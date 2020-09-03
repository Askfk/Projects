close all
clear all
clc

%% 读取传感器数据(personal)
%AS0 = load('NewHotspot_01_12_19_02_06_10.dat','-mat'); % 读取无损数据
AS1 = load('NewHotspot_01_12_19_02_07_07.dat','-mat'); % 读取有损数据
%AS1 = load('NewHotspot_01_12_19_02_06_10.dat','-mat'); % 读取无损数据
%%

fs = 24000 * 1000; % 采样频率
N = 4000; % 采样点数

num_X = 300; % 纵轴长度
num_Y = 400; % 横轴长度
X = 1:1:num_X;
Y = 1:1:num_Y;  % 热力图坐标

beta = 0.2; % β的值

point_num = 6; % 传感器个数

% 输入传感器坐标时需要将世界的横纵坐标交换顺序，例如：实际坐标轴坐标为[50,100],则输入时需要输入[100,50]
p1 = [236.6,150];
p2 = [236.6,250];
p3 = [150,100];
p4 = [150,300];
p5 = [63.4,150];
p6 = [63.4,250];

v_s0 = 5327; % 钢板S0波传播速度单位m/s
v_a0 = 2480; % 钢板A0波传播速度单位m/s

%% 初始化传感器和路径矩阵
for i = 1:point_num
   eval(['p(i,:)=','p',num2str(i),';']) % 传感器坐标矩阵
end

% 传播途径 [i,j]为i发射j接收
path_n = (point_num/2)*(point_num-1); % 传播路径条数
num_path = 1;
for i = 1:point_num                         
   for j = i+1:point_num
       path(num_path,:) = [i,j]; % 传播路径矩阵
       num_path = num_path + 1;
   end
end

num = path_n; % 传播路径数

addp = 500;
for i = 1:num
    path_n = path(i,:); % 获取第i条传播路径
    send_sensor = p(path_n(1),:); % 获取路径驱动器的坐标
    receive_sensor = p(path_n(2),:); % 获取路径接收器的坐标
    Dn(i) = norm(send_sensor - receive_sensor); % 计算驱动器与传感器的距离Dn
    t2 = Dn(i)/1000/v_a0;
    m2 = t2/(1/fs);
    M(i,:) = [floor(m2),floor(m2)+addp];
end

for i = 1:num
   eval(['A1(:,i)=AS1.a',num2str(i-1),'(M(i,1):M(i,2))',';']);
   eval(['S1{i}=AS1.s',num2str(i-1),'(M(i,1):M(i,2))',';']);
end

%%
route1 = [];
route1_line = [];
route2 = [];
route2_line = [];
route3 = [];
route3_line = [];

route1(1,1,:) = S1{1};
route1(2,1,:) = A1(:,1);
route1_line(1) = 12;
route1(1,2,:) = S1{2};
route1(2,2,:) = A1(:,2);
route1_line(2) = 13;
route1(1,3,:) = S1{7};
route1(2,3,:) = A1(:,7);
route1_line(3) = 24;
route1(1,4,:) = S1{11};
route1(2,4,:) = A1(:,11);
route1_line(4) = 35;
route1(1,5,:) = S1{14};
route1(2,5,:) = A1(:,14);
route1_line(5) = 46;
route1(1,6,:) = S1{15};
route1(2,6,:) = A1(:,15);
route1_line(6) = 56;

route2(1,1,:) = S1{3};
route2(2,1,:) = A1(:,3);
route2_line(1) = 14;
route2(1,2,:) = S1{4};
route2(2,2,:) = A1(:,4);
route2_line(2) = 15;
route2(1,3,:) = S1{6};
route2(2,3,:) = A1(:,6);
route2_line(3) = 23;
route2(1,4,:) = S1{9};
route2(2,4,:) = A1(:,9);
route2_line(4) = 26;
route2(1,5,:) = S1{12};
route2(2,5,:) = A1(:,12);
route2_line(5) = 36;
route2(1,6,:) = S1{13};
route2(2,6,:) = A1(:,13);
route2_line(6) = 45;

route3(1,1,:) = S1{5};
route3(2,1,:) = A1(:,5);
route3_line(1) = 16;
route3(1,2,:) = S1{8};
route3(2,2,:) = A1(:,8);
route3_line(2) = 25;
route3(1,3,:) = S1{10};
route3(2,3,:) = A1(:,10);
route3_line(3) = 34;

%% 路径1
[~, a, ~] = size(route1);
index = 1;
for i = 1:a
    for j = 1:a
       if j ~= i
          corr_c = corrcoef(route1(1,i,:),route1(1,j,:));
          route1_corr(1,index) = abs(corr_c(2,1)); 
          route1_corr(2,index) = route1_line(i);
          route1_corr(3,index) = route1_line(j);
          index = index + 1;  
       end
    end
end

for i = 1:a
   sum = 0;
   for j = 1:a-1
       sum = sum + route1_corr(1,(i-1)*(a-1)+j);
   end
   sum_corr1(i) = sum;
end
[~, base_line1_index] = max(sum_corr1);

for i = 1:a
    route1_fft(1,i,:) = fft(route1(1,i,:), N);
    route1_fft(2,i,:) = fft(route1(2,i,:), N);
end
route1_fft(1,:,:) = abs(route1_fft(1,:,:).*conj(route1_fft(1,:,:)));
route1_fft(2,:,:) = abs(route1_fft(2,:,:).*conj(route1_fft(2,:,:)));

DIn_index = 0;
%coef_base1 = corr_specific(route1_fft(1,base_line1_index,:),route1_fft(2,base_line1_index,:), 25, 67);
for i = 1:a
    route1_1 = route1(1,i,:);
    route1_base = route1(1,base_line1_index,:);
    coef = corr(single(route1_1(:)),single(route1_base(:)), 'type','Spearman');
    coef1(i) = coef;
    if i ~= base_line1_index
        DIn_index = DIn_index + 1;
        %DIn(DIn_index) = abs((coef_base1-coef1(i))/coef_base1);
        DIn(DIn_index) = 1 - abs(coef1(i));
        
        line_as = route1_line(i);
        send_sensor = p(fix(line_as/10),:); % 获取路径驱动器的坐标
        receive_sensor = p(mod(line_as,10),:); % 获取路径接收器的坐标
        for j = 1:num_X
            for k = 1:num_Y
                point = [j k];
                dan(num_X-j+1,k) = norm(point - send_sensor); % 计算每个点与驱动器的距离
                dsn(num_X-j+1,k) = norm(point - receive_sensor); % 计算每个点与接收器的距离
            end
        end
        Dan(:,:,DIn_index) = dan;
        Dsn(:,:,DIn_index) = dsn;
    end
end


%% 路径2
[~, a, ~] = size(route2);
index = 1;
for i = 1:a
    for j = 1:a
        if j ~= i
            corr_c = corrcoef(route2(1,i,:),route2(1,j,:));
            route2_corr(1,index) = abs(corr_c(2,1)); 
            route2_corr(2,index) = route2_line(i);
            route2_corr(3,index) = route2_line(j);
            index = index + 1; 
        end
       
    end
end

for i = 1:a
   sum = 0;
   for j = 1:a-1
       sum = sum + route2_corr(1,(i-1)*(a-1)+j);
   end
   sum_corr2(i) = sum;
end
[~, base_line2_index] = max(sum_corr2);

for i = 1:a
    route2_fft(1,i,:) = fft(route2(1,i,:), N);
    route2_fft(2,i,:) = fft(route2(2,i,:), N);
end
route2_fft(1,:,:) = abs(route2_fft(1,:,:).*conj(route2_fft(1,:,:)));
route2_fft(2,:,:) = abs(route2_fft(2,:,:).*conj(route2_fft(2,:,:)));

%coef_base2 = corr_specific(route2_fft(1,base_line2_index,:),route2_fft(2,base_line2_index,:), 25, 67);
for i = 1:a
    route2_1 = route2(1,i,:);
    route2_base = route2(1,base_line2_index,:);
    coef = corr(route2_1(:),route2_base(:), 'type','Spearman');
    coef2(i) = coef;
    if i ~= base_line2_index
        DIn_index = DIn_index + 1;
        %DIn(DIn_index) = abs((coef_base2-coef2(i))/coef_base2);
        DIn(DIn_index) = 1 - abs(coef2(i));
        
        line_as = route2_line(i);
        send_sensor = p(fix(line_as/10),:); % 获取路径驱动器的坐标
        receive_sensor = p(mod(line_as,10),:); % 获取路径接收器的坐标
        for j = 1:num_X
            for k = 1:num_Y
                point = [j k];
                dan(num_X-j+1,k) = norm(point - send_sensor); % 计算每个点与驱动器的距离
                dsn(num_X-j+1,k) = norm(point - receive_sensor); % 计算每个点与接收器的距离
            end
        end
        Dan(:,:,DIn_index) = dan;
        Dsn(:,:,DIn_index) = dsn;
    end
end

%% 路径3
[~, a, ~] = size(route3);
index = 1;
for i = 1:a
    for j = 1:a
        if j ~= i
            corr_c = corrcoef(route3(1,i,:),route3(1,j,:));
            route3_corr(1,index) = abs(corr_c(2,1));
            route3_corr(2,index) = route3_line(i);
            route3_corr(3,index) = route3_line(j);
            index = index + 1;
        end
        
    end
end

for i = 1:a
   sum = 0;
   for j = 1:a-1
       sum = sum + route3_corr(1,(i-1)*(a-1)+j);
   end
   sum_corr3(i) = sum;
end

[~, base_line3_index] = max(sum_corr3);

for i = 1:a
    route3_fft(1,i,:) = fft(route3(1,i,:), N);
    route3_fft(2,i,:) = fft(route3(2,i,:), N);
end
route3_fft(1,:,:) = abs(route3_fft(1,:,:).*conj(route3_fft(1,:,:)));
route3_fft(2,:,:) = abs(route3_fft(2,:,:).*conj(route3_fft(2,:,:)));

%coef_base3 = corr(route3(1,base_line3_index,:),route3(2,base_line3_index,:), 25, 67);
for i = 1:a
    route3_1 = route1(1,i,:);
    route3_base = route1(1,base_line3_index,:);
    coef = corr(route3_1(:),route3_base(:), 'type','Spearman');
    coef3(i) = coef;
    if i ~= base_line3_index
        DIn_index = DIn_index + 1;
        %DIn(DIn_index) = abs((coef_base3-coef3(i))/coef_base3);
        DIn(DIn_index) = 1 - abs(coef3(i));
        
        line_as = route3_line(i);
        send_sensor = p(fix(line_as/10),:); % 获取路径驱动器的坐标
        receive_sensor = p(mod(line_as,10),:); % 获取路径接收器的坐标
        for j = 1:num_X
            for k = 1:num_Y
                point = [j k];
                dan(num_X-j+1,k) = norm(point - send_sensor); % 计算每个点与驱动器的距离
                dsn(num_X-j+1,k) = norm(point - receive_sensor); % 计算每个点与接收器的距离
            end
        end
        Dan(:,:,DIn_index) = dan;
        Dsn(:,:,DIn_index) = dsn;
    end
end


%% 计算Rn(x,y) && Wn[Rn(x,y)]
%Rn = [];
%WnRn = [];
for i = 1:DIn_index
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

%% 计算损伤概率P(x,y)
Possibility = zeros(num_X,num_Y);
for i = 1:DIn_index
    pn = DIn(i) .* WnRn(:,:,i);
    Possibility = Possibility + pn;
end
Possibility = Possibility.^10;

%% 绘制损伤热力图
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
h = heatmap(flipud(Possibility));



