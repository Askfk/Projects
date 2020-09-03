clear all
clc
close all

%%
A = 1.2; % 信号的幅度调制
fc = 220; % 信号中心频率
n = 5; % 峰值个数

AS0 = load('test.dat','-mat');


syms t w a1 a2 a3 a4 a5 l1 l2 l3 l4 l5;
Vt = A * (heaviside(t) - heaviside(t-n/fc)) * (1 - cos(2*pi*fc*t/n)) * sin(2*pi*fc*t); % 激发函数
Fw = fourier(Vt, t, w); % 激发函数的傅里叶变换

kw = w/3120; % lamb波的波数

Ajw = abs(w)/(abs(w)+1);

g1 = a1 * ifourier(Ajw * Fw * exp(i*kw*l1), w, t);
g2 = a2 * ifourier(Fw * exp(i*kw*l2), w, t);
g3 = a3 * ifourier(Fw * exp(i*kw*l3), w, t);
g4 = a4 * ifourier(Fw * exp(i*kw*l4), w, t);
g5 = a5 * ifourier(Fw * exp(i*kw*l5), w, t);

Gt = g1 + g2 + g3 + g4 + g5;

error = 0;
for i = 1:length(AS0.s0)
    t = 1/220000 * i
    error = error + abs(subs(Gt) - AS0.s0(i));
end

%%
lb1=0.1;ub1=2; 
lb2=0.1;ub2=2; 
lb3=0.1;ub3=1; 
lb4=0.1;ub4=1; 
lb5=0.1;ub5=1;

la1=-5;ua1=5;
la2=-5;ua2=5;
la3=-5;ua3=5;
la4=-5;ua4=5;
la5=-5;ua5=5;


NIND=200;        %个体数目
MAXGEN=200;      %最大遗传代数
PRECI=20;       %变量的二进制位数
GGAP=0.95;      %代沟
px=0.9;         %交叉概率
pm=0.01;        %变异概率
trace=zeros(11,MAXGEN);     %寻优结果的初始值
FieldD=[PRECI PRECI PRECI PRECI PRECI PRECI PRECI PRECI PRECI PRECI;la1 la2 la3 la4 la5 lb1 lb2 lb3 lb4 lb5;ua1 ua2 ua3 ua4 ua5 ub1 ub2 ub3 ub4 ub5;1 1 1 1 1 1 1 1 1 1;0 0 0 0 0 0 0 0 0 0;1 1 1 1 1 1 1 1 1 1;1 1 1 1 1 1 1 1 1 1];                      %区域描述器
Chrom=crtbp(NIND,PRECI*10);                      %初始种群

gen=0;                                  %代计数器
XY=bs2rv(Chrom,FieldD);                 %计算初始种群的十进制转换
a1 = XY(:,1);
a2 = XY(:,2);
a3 = XY(:,3);
a4 = XY(:,4);
a5 = XY(:,5);

l1 = XY(:,6);
l2 = XY(:,7);
l3 = XY(:,8);
l4 = XY(:,9);
l5 = XY(:,10);

ObjV = subs(error);

while gen<MAXGEN
   FitnV=ranking(ObjV);                              %分配适应度值
   SelCh=select('sus',Chrom,FitnV,GGAP);              %选择
   SelCh=recombin('xovsp',SelCh,px);                  %重组
   SelCh=mut(SelCh,pm);                               %变异
   XY=bs2rv(SelCh,FieldD);               %子代个体的十进制转换
   a1 = XY(:,1);
   a2 = XY(:,2);
   a3 = XY(:,3);
   a4 = XY(:,4);
   a5 = XY(:,5);

   l1 = XY(:,6);
   l2 = XY(:,7);
   l3 = XY(:,8);
   l4 = XY(:,9);
   l5 = XY(:,10);
   ObjVSel=subs(error);             %计算子代的目标函数值
   [Chrom,ObjV]=reins(Chrom,SelCh,1,1,ObjV,ObjVSel); %重插入子代到父代，得到新种群
   XY=bs2rv(Chrom,FieldD);
   gen=gen+1;                                             %代计数器增加
   %获取每代的最优解及其序号，Y为最优解,I为个体的序号
   [Y,I]=min(ObjV);
   trace(1:10,gen)=XY(I,:);                       %记下每代的最优值
   trace(11,gen)=Y;                               %记下每代的最优值
   gen
end
