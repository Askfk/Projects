close all
clear all
clc

%% ��ȡ����������(personal)
AS0 = load('NewHotspot_01_12_19_02_14_37.dat','-mat'); % ��ȡ��������
AS1 = load('NewHotspot_01_12_19_02_15_51.dat','-mat'); % ��ȡ��������

%% ʱ��Ƶ�������ʼ��(personal)
fs = 24000 * 1000; % ����Ƶ��
N = 4000; % ��������
t = 1/fs:1/fs:N/fs; % ʱ��ʱ����
n = 0:N-1;

fft_N = N; % fft ����2

f = 0:fft_N-1;
f = f*fs/fft_N; % Ƶ��Ƶ��������

%% ��ʼ������ͼ���� && �� && ����������(personal)
num_X = 300; % ���᳤��
num_Y = 400; % ���᳤��
X = 1:1:num_X;
Y = 1:1:num_Y;  % ����ͼ����

beta = 0.2; % �µ�ֵ

point_num = 6; % ����������

% ���봫��������ʱ��Ҫ������ĺ������꽻��˳�����磺ʵ������������Ϊ[50,100],������ʱ��Ҫ����[100,50]
p1 = [236.6,150];
p2 = [236.6,250];
p3 = [150,100];
p4 = [150,300];
p5 = [63.4,150];
p6 = [63.4,250];

v_s0 = 5327; % �ְ�S0�������ٶȵ�λm/s
v_a0 = 2480; % �ְ�A0�������ٶȵ�λm/s

%% ��ʼ����������·������
for i = 1:point_num
   eval(['p(i,:)=','p',num2str(i),';']) % �������������
end

% ����;�� [i,j]Ϊi����j����
path_n = (point_num/2)*(point_num-1); % ����·������
num_path = 1;
for i = 1:point_num                         
   for j = i+1:point_num
       path(num_path,:) = [i,j]; % ����·������
       num_path = num_path + 1;
   end
end

num = path_n; % ����·����

%% ���㲻ͬ·��S�źŲ�����ʼ��m1,m2
addp = 300;
for i = 1:num
    path_num = path(i,:); % ��ȡ��i������·��
    send_sensor = p(path_num(1),:); % ��ȡ·��������������
    receive_sensor = p(path_num(2),:); % ��ȡ·��������������
    Dn(i) = norm(send_sensor - receive_sensor); % �����������봫�����ľ���Dn
    t1 = Dn(i)/1000/v_s0;
    m1 = t1/(1/fs);
    t2 = Dn(i)/1000/v_a0;
    m2 = t2/(1/fs);
    M(i,:) = [floor(m1),floor(m2)+addp];
end    

%% ���������ݳ�ʼ��

%A0 = [];
%A1 = [];
%S0 = [];
%S1 = [];
% ��������״̬�·��մ��������ݳ�ʼ��
for i = 1:num
   eval(['A0(:,i)=AS0.a',num2str(i-1),';']);
   eval(['S0{i}=AS0.s',num2str(i-1),'(M(i,1):M(i,2))',';']);
   eval(['A1(:,i)=AS1.a',num2str(i-1),';']);
   eval(['S1{i}=AS1.s',num2str(i-1),'(M(i,1):M(i,2))',';']);
end

%% ����ʱ���źţ���ʱûд��
%figure(3)
%plot(t,A0(:,3))

%% ���ٸ���Ҷ�任 ��ͼʱy = fft * 2 / N
%Abn = [];
%Cbn = [];
%Adn = [];
%Cdn = [];
for i = 1:num
    Abn(:,i) = fft(A0(:,i), fft_N);
    Cbn(:,i) = fft(S0{i}, fft_N);
    Adn(:,i) = fft(A1(:,i), fft_N);
    Cdn(:,i) = fft(S1{i}, fft_N);
end

%% ����������
Abn = abs(Abn.*conj(Abn));
Cbn = abs(Cbn.*conj(Cbn));
Adn = abs(Adn.*conj(Adn));
Cdn = abs(Cdn.*conj(Cdn));

%% ����������
figure(1)
for i = 1:num
    subplot(num,2,2*i-1)
    plot(f, Abn(:,i)/fft_N/max(Abn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold on
    plot(f, Cbn(:,i)/fft_N/max(Cbn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold off
    title("a0-s0-" + i)
    subplot(num,2,2*i)
    plot(f, Adn(:,i)/fft_N/max(Adn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold on
    plot(f, Cdn(:,i)/fft_N/max(Cdn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold off
    title("a1-s1-" + i)
end

%% �����ϵ�� && ����ָ��DI
%coefbn = [];
%coefdn = [];
%DIn = [];
for i = 1:num
    coefbn_list = corrcoef(Abn(:,i), Cbn(:,i));
    coefdn_list = corrcoef(Adn(:,i), Cdn(:,i));
    coefbn(i) = coefbn_list(1,2); % ���������ϵ��
    coefdn(i) = coefdn_list(1,2); % ���������ϵ��
    DIn(i) = abs((coefbn(i) - coefdn(i))/coefbn(i)); % ����ָ����DI��
    %coefdin = corrcoef(Cbn(:,i),Cdn(:,i));
    %DIn(i) = coefdin(1,2);
end

%% ���� Dan(x,y) && Dsn(x,y) && Dn
%Dan = [];
%Dsn = [];
%Dn = [];
for i = 1:num
    %dan = [];
    %dsn = [];
    path_num = path(i,:); % ��ȡ��i������·��
    send_sensor = p(path_num(1),:); % ��ȡ·��������������
    receive_sensor = p(path_num(2),:); % ��ȡ·��������������
    for j = 1:num_X
       for k = 1:num_Y
           point = [j k];
           dan(num_X-j+1,k) = norm(point - send_sensor); % ����ÿ�������������ľ���
           dsn(num_X-j+1,k) = norm(point - receive_sensor); % ����ÿ������������ľ���
       end
    end
    Dan(:,:,i) = dan;
    Dsn(:,:,i) = dsn;
end

%% ����Rn(x,y) && Wn[Rn(x,y)]
%Rn = [];
%WnRn = [];
for i = 1:num
   %rn = [];
   %wnrn = [];
   dan = Dan(:,:,i);
   dsn = Dsn(:,:,i);
   dn = Dn(i);
   for j = 1:num_X
      for k = 1:num_Y
          rn(j,k) = (dan(j,k)+dsn(j,k))/dn - 1;
          if (rn(j,k) < beta)
              wnrn(j,k) = 1 - rn(j,k)/beta;
          else 
              wnrn(j,k) = 0;
          end
      end
   end
   Rn(:,:,i) = rn;
   WnRn(:,:,i) = wnrn;
end

%% �������˸���P(x,y)
Possibility = zeros(num_X,num_Y);
for i = 1:num
    pn = DIn(i) .* WnRn(:,:,i);
    Possibility = Possibility + pn;
end
Possibility = Possibility.^10;

%% ������������ͼ
%maxP = max(max(Possibility));
%lambda = maxP*0.8;
%for i = 1:30
%   for j = 1:400
%      if Possibility(i,j) < lambda
%          Possibility(i,j) = 0;
%      end
%   end
%end
figure(2)
h = heatmap(Possibility);

