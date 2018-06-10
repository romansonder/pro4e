%
% O2 Lichtgeschwindigkeit Fehlerrechnung
%
%
clear all, close all, clc
%==========================================================================
% Messung 1
%==========================================================================
%Konstante
s2=4.82;
s1=0.12;
f1=1;
f2=4.92;
%==========================================================================
%Werte aus Fit
f=624;                  %Frequenz
c=294.84e6;             %Lichtgeschwindigkeit c1 aus Fit
sc=2.16e6;               %Fehler c1 aus Fit
%==========================================================================
%Berechnung
x=4*2*pi*f*(s2+f2)*f1/c; %Resultierendes x
w=2*pi*f;
sf1=5e-3;
sf2=5e-3;
ss2=5e-3;
ds2=4*f1*w/x;            %Ableitung nach s2
df2=4*f1*w/x;            %Ableitung nach f2
df1=4*(f2+s2)*w/x;       %Ableitung nach f1
%==========================================================================
%Resultierender Fehler
sr1=sqrt((sc)^2+(ds2*ss2)^2+(df1*sf1)^2+(df2*sf2)^2)

%==========================================================================
% Messung 2
%==========================================================================
%Werte aus Fit
f=625;                  %Frequenz
c=299.84e6;             %Lichtgeschwindigkeit c1 aus Fit
sc=3.83e6;               %Fehler c1 aus Fit
%==========================================================================
%Berechnung
x=4*2*pi*f*(s2+f2)*f1/c; %Resultierendes x
w=2*pi*f;
sf1=5e-3;
sf2=5e-3;
ss2=5e-3;
ds2=4*f1*w/x;            %Ableitung nach s2
df2=4*f1*w/x;            %Ableitung nach f2
df1=4*(f2+s2)*w/x;       %Ableitung nach f1
%==========================================================================
%Resultierender Fehler
sr2=sqrt((sc)^2+(ds2*ss2)^2+(df1*sf1)^2+(df2*sf2)^2)