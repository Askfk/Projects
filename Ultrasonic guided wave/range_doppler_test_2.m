%% &&&&&&&&&&
% date:2018.3.6
% 点目标RDA算法
%% &&&&&&&&&&
clear;
clc;
close all;
C=3e8;            %光速
Fc=1e9;           %载波频率1GHz
lambda=C/Fc;      %波长
 
%%目标区域参数
Xmin=0;     %目标区域方位向范围
Xmax=50;    %[Xmin,Xmax]
Yc=10000;   %成像区域中线
Y0=500;     %目标区域距离向范围[Yc-Y0,Yc+Y0],成像宽度为2*Y0
%%轨道参数
V=100;              %雷达平台速度100m/s
H=0;                %雷达平台高度
R0=sqrt(Yc^2+H^2);  %最短距离
%%天线参数
D=4;                %方位向天线孔径长度
Lsar=lambda*R0/D;   %SAR合成孔径长度
Tsar=Lsar/V;        %合成孔径时间
 
%% 慢时间域参数（方位向）
Ka=-2*V^2/lambda/R0;%线性调频率
Ba=abs(Ka*Tsar);%调制带宽
PRF=2*Ba;       %脉冲重复频率
PRT=1/PRF;      %脉冲重复时间
ds=PRT;         %脉冲重复周期 
Nslow=ceil((Xmax-Xmin+Lsar)/V/ds);%脉冲数，ceil为取整函数
Nslow=2^nextpow2(Nslow); %量化为2的指数
sn=linspace((Xmin-Lsar/2)/V,(Xmax+Lsar/2)/V,Nslow);   %慢时域的时间矩阵
PRT=(Xmax-Xmin+Lsar)/V/Nslow;    %更新脉冲重复时间   
PRF=1/PRT; % 更新脉冲重复频率
fa=linspace(-0.5*PRF,0.5*PRF,Nslow);%慢时间域频率（方位频率）
 
%% 快时间域参数（距离向）
Tr=5e-6;    %脉冲宽度（脉冲持续时间5us）
Br=30e6;    %chirp频率调制带宽为30MHz
Kr=Br/Tr;   %chirp调频率
Fsr=2*Br;                         %快时间域采样频率，为3倍带宽
dt=1/Fsr;                         %快时间域采样间隔
Rmin=sqrt((Yc-Y0)^2+H^2);               %近距离点
Rmax=sqrt((Yc+Y0)^2+H^2+(Lsar/2)^2);    %远距离点
Nfast=ceil(2*(Rmax-Rmin)/C/dt+Tr/dt);   %快时域采样数量
Nfast=2^nextpow2(Nfast);                %更新为2的幂次
tm=linspace(2*Rmin/C,2*Rmax/C+Tr,Nfast);%快时域的离散时间矩阵
dt=(2*Rmax/C+Tr-2*Rmin/C)/Nfast;   %更新间隔
Fsr=1/dt;
fr=linspace(-0.5*Fsr,0.5*Fsr,Nfast);%快时间域频率（距离频率）
 
%% 分辨率参数设置
DY=C/2/Br;    %距离向分辨率
DX=D/2;       %方位向分辨率
 
%% 目标设定
Ntarget=3;   %目标数目
Ptarget=[Xmin,Yc,1               %目标位置
         Xmin,Yc+10*DY,1
         Xmin+20*DX,Yc+50*DY,1]; 
K=Ntarget;    %目标数目
N=Nslow;    %慢时间采样数
M=Nfast;     %快时间采样数
T=Ptarget;   %目标位置
 
%% 1.回波信号
Srnm=zeros(N,M);                        %生成零矩阵存储回波信号
for k=1:1:K                             %总共K个目标
    sigma=T(k,3);                       %得到目标的反射系数
    Dslow=sn*V-T(k,1);                  %方位向距离，投影到方位向的距离
    R=sqrt(Dslow.^2+T(k,2)^2+H^2);      %实际距离矩阵
    tau=2*R/C;                          %信号延时
    Dfast=ones(N,1)*tm-tau'*ones(1,M);  %时间矩阵
    phase=pi*Kr*Dfast.^2-(4*pi/lambda)*(R'*ones(1,M));  %相位
    Srnm=Srnm+sigma*exp(1i*phase).*(0<Dfast&Dfast<Tr).*((abs(Dslow)<Lsar/2)'*ones(1,M)); %多目标回波叠加
end
%% 图形一
figure(1) 
subplot(211) 
imagesc(abs(Srnm));title('SAR data') 
subplot(212) 
imagesc(angle(Srnm)) 
row=tm*C/2;  %% 行
col=sn*V;    %% 列
%% 2.距离压缩
tr=tm-2*Rmin/C;
Refr=exp(1i*pi*Kr*tr.^2).*(0<tr&tr<Tr);%距离压缩参考函数
F_Refr=fft((Refr));
Sr=zeros(N,M);
for k2=1:1:M
    temp1=fft(Srnm(k2,:));
    FSrnm=temp1.*conj(F_Refr);
    Sr(k2,:)=ifft(FSrnm);
end
%% 图形二
figure(2)
colormap(gray)
imagesc(row,col,255-abs(Sr));
title('距离压缩'),xlabel('距离向'),ylabel('方位向');
%% 3.方位向傅里叶变换
S_rd = fftshift(fft(fftshift(Sr,1)),1);  % 方位FFT
%% 图形三
figure(3)
colormap(gray)
imagesc(row,col,255-abs(S_rd));
title('方位fft信号'),xlabel('距离向'),ylabel('方位向');
%% 4.距离徙动校正
tau_m = ones(N, 1)*tm;      % 距离时间矩阵
f_eta_m = fa'*ones(1,M);    % 方位频率矩阵
f_tau_m = ones(N,1)*fr;     % 距离频率矩阵
delta_R = lambda^2*Fsr/8/V^2*tau_m.*f_eta_m.^2;    % 需要校正的RCM
%delta_R = lambda^2*R0/8/V^2*f_eta_m.^2;           %% 上下两个delta_R公式都可以用
Grcmc = exp(1i*4*pi*f_tau_m.*delta_R/C);    % 相位乘法器
temp = fftshift(fft(fftshift(S_rd,2),[],2),2).*Grcmc;   % 线性相位相乘
S_rcmc = ifftshift(ifft(ifftshift(temp,2),[],2),2);   % IFFT
%% 图形四
figure(4)
colormap(gray)
imagesc(row,col,255-abs(S_rcmc));
title('RCMC后的信号幅度'),xlabel('距离向'),ylabel('方位向');
   
%% 5.方位压缩
Ka = 2*V^2/lambda./(tau_m*C/2);    % 方位向调频率
Hf_ac = exp(-1i*pi*(f_eta_m).^2./Ka);   % 频率匹配滤波器
Sac_f = S_rcmc.*Hf_ac;    % 方位压缩
Sac_t = ifftshift(ifft(ifftshift(Sac_f,1)),1);  % 方位向IFFT
%% 图形五
figure(5);
colormap(gray);
imagesc(row,col,255-abs(Sac_t));
title('方位压缩'),xlabel('距离向'),ylabel('方位向');