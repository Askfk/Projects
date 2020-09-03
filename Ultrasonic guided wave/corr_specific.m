function [corr] = corr_specific(Cn, An, start_p, end_p)
    
    %Cn = Cn/10^10
    %An = An/10^10
    can_sum = 0;
    cn_sum = 0;
    an_sum = 0;
    for i = start_p:end_p
        can_sum = (Cn(i)*An(i))^2 + can_sum;
        cn_sum = Cn(i)^2 + cn_sum;
        an_sum = An(i)^2 + an_sum;
    end
    corr = 1 - (can_sum/(cn_sum*an_sum))^0.5;
end