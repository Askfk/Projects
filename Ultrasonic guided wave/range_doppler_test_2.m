%% &&&&&&&&&&
% date:2018.3.6
% ��Ŀ��RDA�㷨
%% &&&&&&&&&&
clear;
clc;
close all;
C=3e8;            %����
Fc=1e9;           %�ز�Ƶ��1GHz
lambda=C/Fc;      %����
 
%%Ŀ���������
Xmin=0;     %Ŀ������λ��Χ
Xmax=50;    %[Xmin,Xmax]
Yc=10000;   %������������
Y0=500;     %Ŀ�����������Χ[Yc-Y0,Yc+Y0],������Ϊ2*Y0
%%�������
V=100;              %�״�ƽ̨�ٶ�100m/s
H=0;                %�״�ƽ̨�߶�
R0=sqrt(Yc^2+H^2);  %��̾���
%%���߲���
D=4;                %��λ�����߿׾�����
Lsar=lambda*R0/D;   %SAR�ϳɿ׾�����
Tsar=Lsar/V;        %�ϳɿ׾�ʱ��
 
%% ��ʱ�����������λ��
Ka=-2*V^2/lambda/R0;%���Ե�Ƶ��
Ba=abs(Ka*Tsar);%���ƴ���
PRF=2*Ba;       %�����ظ�Ƶ��
PRT=1/PRF;      %�����ظ�ʱ��
ds=PRT;         %�����ظ����� 
Nslow=ceil((Xmax-Xmin+Lsar)/V/ds);%��������ceilΪȡ������
Nslow=2^nextpow2(Nslow); %����Ϊ2��ָ��
sn=linspace((Xmin-Lsar/2)/V,(Xmax+Lsar/2)/V,Nslow);   %��ʱ���ʱ�����
PRT=(Xmax-Xmin+Lsar)/V/Nslow;    %���������ظ�ʱ��   
PRF=1/PRT; % ���������ظ�Ƶ��
fa=linspace(-0.5*PRF,0.5*PRF,Nslow);%��ʱ����Ƶ�ʣ���λƵ�ʣ�
 
%% ��ʱ���������������
Tr=5e-6;    %�����ȣ��������ʱ��5us��
Br=30e6;    %chirpƵ�ʵ��ƴ���Ϊ30MHz
Kr=Br/Tr;   %chirp��Ƶ��
Fsr=2*Br;                         %��ʱ�������Ƶ�ʣ�Ϊ3������
dt=1/Fsr;                         %��ʱ����������
Rmin=sqrt((Yc-Y0)^2+H^2);               %�������
Rmax=sqrt((Yc+Y0)^2+H^2+(Lsar/2)^2);    %Զ�����
Nfast=ceil(2*(Rmax-Rmin)/C/dt+Tr/dt);   %��ʱ���������
Nfast=2^nextpow2(Nfast);                %����Ϊ2���ݴ�
tm=linspace(2*Rmin/C,2*Rmax/C+Tr,Nfast);%��ʱ�����ɢʱ�����
dt=(2*Rmax/C+Tr-2*Rmin/C)/Nfast;   %���¼��
Fsr=1/dt;
fr=linspace(-0.5*Fsr,0.5*Fsr,Nfast);%��ʱ����Ƶ�ʣ�����Ƶ�ʣ�
 
%% �ֱ��ʲ�������
DY=C/2/Br;    %������ֱ���
DX=D/2;       %��λ��ֱ���
 
%% Ŀ���趨
Ntarget=3;   %Ŀ����Ŀ
Ptarget=[Xmin,Yc,1               %Ŀ��λ��
         Xmin,Yc+10*DY,1
         Xmin+20*DX,Yc+50*DY,1]; 
K=Ntarget;    %Ŀ����Ŀ
N=Nslow;    %��ʱ�������
M=Nfast;     %��ʱ�������
T=Ptarget;   %Ŀ��λ��
 
%% 1.�ز��ź�
Srnm=zeros(N,M);                        %���������洢�ز��ź�
for k=1:1:K                             %�ܹ�K��Ŀ��
    sigma=T(k,3);                       %�õ�Ŀ��ķ���ϵ��
    Dslow=sn*V-T(k,1);                  %��λ����룬ͶӰ����λ��ľ���
    R=sqrt(Dslow.^2+T(k,2)^2+H^2);      %ʵ�ʾ������
    tau=2*R/C;                          %�ź���ʱ
    Dfast=ones(N,1)*tm-tau'*ones(1,M);  %ʱ�����
    phase=pi*Kr*Dfast.^2-(4*pi/lambda)*(R'*ones(1,M));  %��λ
    Srnm=Srnm+sigma*exp(1i*phase).*(0<Dfast&Dfast<Tr).*((abs(Dslow)<Lsar/2)'*ones(1,M)); %��Ŀ��ز�����
end
%% ͼ��һ
figure(1) 
subplot(211) 
imagesc(abs(Srnm));title('SAR data') 
subplot(212) 
imagesc(angle(Srnm)) 
row=tm*C/2;  %% ��
col=sn*V;    %% ��
%% 2.����ѹ��
tr=tm-2*Rmin/C;
Refr=exp(1i*pi*Kr*tr.^2).*(0<tr&tr<Tr);%����ѹ���ο�����
F_Refr=fft((Refr));
Sr=zeros(N,M);
for k2=1:1:M
    temp1=fft(Srnm(k2,:));
    FSrnm=temp1.*conj(F_Refr);
    Sr(k2,:)=ifft(FSrnm);
end
%% ͼ�ζ�
figure(2)
colormap(gray)
imagesc(row,col,255-abs(Sr));
title('����ѹ��'),xlabel('������'),ylabel('��λ��');
%% 3.��λ����Ҷ�任
S_rd = fftshift(fft(fftshift(Sr,1)),1);  % ��λFFT
%% ͼ����
figure(3)
colormap(gray)
imagesc(row,col,255-abs(S_rd));
title('��λfft�ź�'),xlabel('������'),ylabel('��λ��');
%% 4.�����㶯У��
tau_m = ones(N, 1)*tm;      % ����ʱ�����
f_eta_m = fa'*ones(1,M);    % ��λƵ�ʾ���
f_tau_m = ones(N,1)*fr;     % ����Ƶ�ʾ���
delta_R = lambda^2*Fsr/8/V^2*tau_m.*f_eta_m.^2;    % ��ҪУ����RCM
%delta_R = lambda^2*R0/8/V^2*f_eta_m.^2;           %% ��������delta_R��ʽ��������
Grcmc = exp(1i*4*pi*f_tau_m.*delta_R/C);    % ��λ�˷���
temp = fftshift(fft(fftshift(S_rd,2),[],2),2).*Grcmc;   % ������λ���
S_rcmc = ifftshift(ifft(ifftshift(temp,2),[],2),2);   % IFFT
%% ͼ����
figure(4)
colormap(gray)
imagesc(row,col,255-abs(S_rcmc));
title('RCMC����źŷ���'),xlabel('������'),ylabel('��λ��');
   
%% 5.��λѹ��
Ka = 2*V^2/lambda./(tau_m*C/2);    % ��λ���Ƶ��
Hf_ac = exp(-1i*pi*(f_eta_m).^2./Ka);   % Ƶ��ƥ���˲���
Sac_f = S_rcmc.*Hf_ac;    % ��λѹ��
Sac_t = ifftshift(ifft(ifftshift(Sac_f,1)),1);  % ��λ��IFFT
%% ͼ����
figure(5);
colormap(gray);
imagesc(row,col,255-abs(Sac_t));
title('��λѹ��'),xlabel('������'),ylabel('��λ��');