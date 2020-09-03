close all
clear

%% 读取传感器数据(personal)
AS0 = load('new_coupon_01_12_19_00_04_36.dat','-mat'); % 读取无损数据
AS1 = load('new_coupon_01_12_19_00_10_37.dat','-mat'); % 读取有损数据

%% 初始化热力图坐标 && β && 传感器坐标(personal)
num_X = 300;
num_Y = 400;
X = 1:1:num_X;
Y = 1:1:num_Y;  % 热力图坐标

beta = 0.2; % β的值 

point_num = 4; % 传感器个数

p1 = [230,300];  % 传感器1坐标
p2 = [70,300];   % 传感器2坐标
p3 = [230,100];  % 传感器3坐标
p4 = [70,100];   % 传感器4坐标

q1 = [300,230];
q2 = [300,70];
q3 = [100,230];
q4 = [100,70];

p = [p1;p2;p3;p4];
%p = [q1;q2;q3;q4];

% 传播途径 [i,j]为i发射j接收
% 传播途径 [i,j]为i发射j接收
path_num = (point_num/2)*point_num; % 传播路径条数
num_path = 1;
for i = 1:point_num
   for j = i+1:point_num
       path(num_path,:) = [i,j]; % 传播路径矩阵
       num_path = num_path + 1;
   end
end
%path = [[1,2];[1,3];[1,4];[2,3];[2,4];[3,4]];

%% 时域频域参数初始化(personal)
fs = 24000 * 1000; % 采样频率
N = 4000; % 采样点数
t = 1/fs:1/fs:N/fs; % 时域时间轴
n = 0:N-1; 

fft_N = N; % fft 参数2

f = 0:fft_N-1;
f = f*fs/fft_N; % 频域频率轴坐标

%% 传感器数据初始化
num = (length(fieldnames(AS0))-1)/2; % 传播路径数
A0 = [];
A1 = [];
S0 = [];
S1 = [];
% 有损无损状态下发收传感器数据初始化
for i = 1:num
   eval(['A0(:,i)','=','AS0.a',num2str(i-1),';']);
   eval(['S0(:,i)','=','AS0.s',num2str(i-1),'(1000:2000)',';']);
   eval(['A1(:,i)','=','AS1.a',num2str(i-1),';']);
   eval(['S1(:,i)','=','AS1.s',num2str(i-1),'(1000:2000)',';']);
end

%% 绘制时域信号
%figure(3)
%plot(t,A0(:,3))

%% 快速傅里叶变换 绘图时y = fft * 2 / N
Abn = [];
Cbn = [];
Adn = [];
Cdn = [];
for i = 1:num
    Abn(:,i) = fft(A0(:,i), fft_N);
    Cbn(:,i) = fft(S0(:,i), fft_N);
    Adn(:,i) = fft(A1(:,i), fft_N);
    Cdn(:,i) = fft(S1(:,i), fft_N);
end

%% 计算能量谱
Abn = abs(Abn.*conj(Abn));
Cbn = abs(Cbn.*conj(Cbn));
Adn = abs(Adn.*conj(Adn));
Cdn = abs(Cdn.*conj(Cdn));

%% 绘制能量谱
figure(1)
for i = 1:num
    subplot(6,2,2*i-1)
    plot(f, Abn(:,i)/fft_N/max(Abn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold on
    plot(f, Cbn(:,i)/fft_N/max(Cbn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold off
    title("a0-s0-" + i)
    subplot(6,2,2*i)
    plot(f, Adn(:,i)/fft_N/max(Adn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold on
    plot(f, Cdn(:,i)/fft_N/max(Cdn(:,i)/fft_N));
    axis([10^5,4.5*10^5,0,1])
    hold off
    title("a1-s1-" + i)
end

%% 自相关系数 && 损伤指数DI
coefbn = [];
coefdn = [];
DIn = [];
for i = 1:num
    coefbn_list = corrcoef(Abn(:,i), Cbn(:,i));
    coefdn_list = corrcoef(Adn(:,i), Cdn(:,i));
    coefbn(i) = coefbn_list(1,2); % 无损自相关系数
    coefdn(i) = coefdn_list(1,2); % 有损自相关系数
    DIn(i) = abs((coefbn(i) - coefdn(i))/coefbn(i)); % 损伤指数（DI）
    %coefdin = corrcoef(Cbn(:,i),Cdn(:,i));
    %DIn(i) = coefdin(1,2);
end

%% 计算 Dan(x,y) && Dsn(x,y) && Dn
Dan = [];
Dsn = [];
Dn = [];
for i = 1:num
    dan = [];
    dsn = [];
    path_n = path(i,:); % 获取第i条传播路径
    send_sensor = p(path_n(1),:); % 获取路径驱动器的坐标
    receive_sensor = p(path_n(2),:); % 获取路径接收器的坐标
    Dn(i) = norm(send_sensor - receive_sensor); % 计算驱动器与传感器的距离Dn
    for j = 1:num_X
       for k = 1:num_Y
           point = [j k];
           dan(num_X-j+1,k) = norm(point - send_sensor); % 计算每个点与驱动器的距离
           dsn(num_X-j+1,k) = norm(point - receive_sensor); % 计算每个点与接收器的距离
       end
    end
    Dan(:,:,i) = dan;
    Dsn(:,:,i) = dsn;
end

%% 计算Rn(x,y) && Wn[Rn(x,y)]
Rn = [];
WnRn = [];
for i = 1:num
   rn = [];
   wnrn = [];
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

%% 计算损伤概率P(x,y)
Possibility = zeros(300,400);
for i = 1:num
    pn = DIn(i) .* WnRn(:,:,i);
    Possibility = Possibility + pn;
end
Possibility = Possibility.^10;

%% 绘制损伤热力图
maxP = max(max(Possibility));
if maxP > 0.04
    lambda = maxP*0.8;
else
    lambda = maxP*0.8;
end
for i = 1:30
   for j = 1:400
      if Possibility(i,j) < lambda
          Possibility(i,j) = 0;
      end
   end
end
figure(2)
h = heatmap(Possibility);

