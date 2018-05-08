# coding: utf-8
import scipy
import sympy
from numpy import *
from scipy.optimize import curve_fit
import matplotlib.pyplot as plot


import pylab

################################################################################
# Funktionsdefinitionen
################################################################################

# read a .txt file to an array
def readarray(filename):
	array = loadtxt(filename, dtype="float", comments='#', delimiter="\t", converters=None, skiprows=0, usecols=None, unpack=False, ndmin=0)
	return array

# save an array to a .txt file
def savearray(array, filename):
	savetxt(filename, array, fmt='%.18e', delimiter='\t', newline='\n', header='', footer='', comments='# ')

# Berechnung des Mittelwerte
def f_mittelwert_a(array):
	res = 0
	for i in range(len(array)):
		res = res + array[i]
	return res/(len(array))

# Berechnung der Unsicherheit des Mittelwert
def f_uns_mw_a(array,m):
	sum_res = 0
	for i in range(len(array)):
		sum_res = sum_res + (array[i]-m)**(2)
	return sqrt((sum_res)/((len(array))*((len(array)) -1)))

################################################################################
# Messresultate einlesen
################################################################################
messung_1 = []		# Messung 1
messung_2 = []		# Messung 2
messung_3 = []		# Messung 3
messung_4 = []		# Messung 4
messung_5 = []		# Messung 5
messung_6 = []		# Messung 6

messung_7 = []		# Messung 7
messung_8 = []		# Messung 8
messung_9 = []		# Messung 9
messung_10 = []		# Messung 10
messung_11 = []		# Messung 11
messung_12 = []		# Messung 12

messung_1 = (readarray("13_messung_1_1.txt").transpose())
messung_2 = (readarray("13_messung_1_2.txt").transpose())
messung_3 = (readarray("13_messung_2_0.txt").transpose())
messung_4 = (readarray("13_messung_2_1.txt").transpose())
messung_5 = (readarray("13_messung_3_0.txt").transpose())
messung_6 = (readarray("13_messung_3_1.txt").transpose())


messung_7 = (readarray("13_messung_4_0.txt").transpose())
messung_8 = (readarray("13_messung_4_1.txt").transpose())
messung_9 = (readarray("13_messung_5_0.txt").transpose())
messung_10 = (readarray("13_messung_5_1.txt").transpose())
messung_11 = (readarray("13_messung_6_0.txt").transpose())
messung_12 = (readarray("13_messung_6_1.txt").transpose())

#messung_1.append(readarray("13_messung_1_0.txt").transpose())
#messung_5 = (readarray("13_messung_5_0.txt").transpose())

################################################################################
# Variablen ueber alle Messungen
################################################################################
LABEL_S = 17
L_LASER = 6.328e-07

################################################################################
# Auswertung Aufgabe 1
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 1')
print('================================================================================')

def f_messung_1(m,b):
	return arcsin(m*L_LASER/b)

dist = 2.344

messung_1[1] = arctan(messung_1[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_1, messung_1[0], messung_1[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_1_fit = linspace(min(messung_1[0]), max(messung_1[0]), 5000)
y_messung_1_fit = f_messung_1(t_messung_1_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Spalt 0.1mm', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_1[0], messung_1[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_1_fit, y_messung_1_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#plot.show()
fig.savefig('graphics/messung_1_1.png')

################################################################################
# Auswertung Aufgabe 2
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 2')
print('================================================================================')

def f_messung_2(m,b):
	return arcsin(m*L_LASER/b)

dist = 2.344

messung_2[1] = arctan(messung_2[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_2, messung_2[0], messung_2[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_2_fit = linspace(min(messung_2[0]), max(messung_2[0]), 5000)
y_messung_2_fit = f_messung_2(t_messung_2_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Spalt $30\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_2[0], messung_2[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_2_fit, y_messung_2_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_1_2.png')

################################################################################
# Auswertung Aufgabe 3
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 3')
print('================================================================================')

def f_messung_3(m,d):
	return arcsin((3.238 + (m-3))*L_LASER/d)

dist = 1.383

messung_3[1] = arctan(messung_3[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_3, messung_3[0], messung_3[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_3_fit = linspace(min(messung_3[0]), max(messung_3[0]), 5000)
y_messung_3_fit = f_messung_3(t_messung_3_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Loch $150\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_3[0], messung_3[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_3_fit, y_messung_3_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_2_0.png')

################################################################################
# Auswertung Aufgabe 4
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 4')
print('================================================================================')

def f_messung_4(m,d):
	return arcsin((3.238 + (m-3))*L_LASER/d)

dist = 0.769

messung_4[1] = arctan(messung_4[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_4, messung_4[0], messung_4[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_4_fit = linspace(min(messung_4[0]), max(messung_4[0]), 5000)
y_messung_4_fit = f_messung_4(t_messung_4_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Loch $100\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_4[0], messung_4[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_4_fit, y_messung_4_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_2_1.png')

################################################################################
# Auswertung Aufgabe 5
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 5')
print('================================================================================')

def f_messung_5(m,b):
	return arcsin(m*L_LASER/b)

dist = 0.676

messung_5[1] = arctan(messung_5[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_5, messung_5[0], messung_5[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_5_fit = linspace(min(messung_5[0]), max(messung_5[0]), 5000)
y_messung_5_fit = f_messung_5(t_messung_5_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Gitter 80 lines/mm m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_5[0], messung_5[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_5_fit, y_messung_5_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_3_0.png')

################################################################################
# Auswertung Aufgabe 6
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 6')
print('================================================================================')

def f_messung_6(m,b):
	return arcsin(m*L_LASER/b)

dist = 0.426

messung_6[1] = arctan(messung_6[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_6, messung_6[0], messung_6[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_6_fit = linspace(min(messung_6[0]), max(messung_6[0]), 5000)
y_messung_6_fit = f_messung_6(t_messung_6_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Direktes Beobachten: Gitter 100 lines/mm$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_6[0], messung_6[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_6_fit, y_messung_6_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_3_1.png')

################################################################################
# Auswertung Aufgabe 7
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 7')
print('================================================================================')

def f_messung_7(m,b):
	return arcsin(m*L_LASER/b)

dist = 1.0

messung_7[1] = arctan(messung_7[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_7, messung_7[0], messung_7[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_7_fit = linspace(min(messung_7[0]), max(messung_7[0]), 5000)
y_messung_7_fit = f_messung_7(t_messung_7_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten Durch Linse: Spalt 0.1mm', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_7[0], messung_7[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_7_fit, y_messung_7_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#plot.show()
fig.savefig('graphics/messung_4_0.png')

################################################################################
# Auswertung Aufgabe 8
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 8')
print('================================================================================')

def f_messung_8(m,b):
	return arcsin(m*L_LASER/b)

dist = 1.0

messung_8[1] = arctan(messung_8[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_8, messung_8[0], messung_8[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_8_fit = linspace(min(messung_8[0]), max(messung_8[0]), 5000)
y_messung_8_fit = f_messung_8(t_messung_8_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten durch Linse: Spalt $30\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_8[0], messung_8[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_8_fit, y_messung_8_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_4_1.png')

################################################################################
# Auswertung Aufgabe 9
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 9')
print('================================================================================')

def f_messung_9(m,d):
	return arcsin((3.238 + (m-3))*L_LASER/d)

dist = 1.0

messung_9[1] = arctan(messung_9[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_9, messung_9[0], messung_9[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_9_fit = linspace(min(messung_9[0]), max(messung_9[0]), 5000)
y_messung_9_fit = f_messung_9(t_messung_9_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten durch Linse: Loch $150\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_9[0], messung_9[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_9_fit, y_messung_9_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_5_0.png')

################################################################################
# Auswertung Aufgabe 10
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 10')
print('================================================================================')

def f_messung_10(m,d):
	return arcsin((3.238 + (m-3))*L_LASER/d)

dist = 1

messung_10[1] = arctan(messung_10[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_10, messung_10[0], messung_10[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_10_fit = linspace(min(messung_10[0]), max(messung_10[0]), 5000)
y_messung_10_fit = f_messung_10(t_messung_10_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten durch Linse: Loch $100\mu m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_10[0], messung_10[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_10_fit, y_messung_10_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_5_1.png')

################################################################################
# Auswertung Aufgabe 11
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 11')
print('================================================================================')

def f_messung_11(m,b):
	return arcsin(m*L_LASER/b)

dist = 1.0

messung_11[1] = arctan(messung_11[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_11, messung_11[0], messung_11[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_11_fit = linspace(min(messung_11[0]), max(messung_11[0]), 5000)
y_messung_11_fit = f_messung_11(t_messung_11_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten durch Linse: Gitter $80 lines/mm m$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_11[0], messung_11[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_11_fit, y_messung_11_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_6_0.png')

################################################################################
# Auswertung Aufgabe 12
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 12')
print('================================================================================')

def f_messung_12(m,b):
	return arcsin(m*L_LASER/b)

dist = 2.0

messung_12[1] = arctan(messung_12[1]/(dist))

# Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
popt, pcov = curve_fit(f_messung_12, messung_12[0], messung_12[1],p0=[1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))



################################################################################
# Figur plotten
################################################################################
t_messung_12_fit = linspace(min(messung_12[0]), max(messung_12[0]), 5000)
y_messung_12_fit = f_messung_12(t_messung_12_fit, popt[0])

plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,5))
fig.suptitle('Beobachten durch Linse: Gitter $100 lines/mm$', fontsize=LABEL_S)
plot.xlabel('Ordnung der Interferenz',fontsize=LABEL_S)
plot.ylabel('Winkel $\phi$ der Inerferenz',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

plot.plot(messung_12[0], messung_12[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_12_fit, y_messung_12_fit, '-', label='Fit', color='black')

plot.legend(fontsize=LABEL_S)
#  plot.show()
fig.savefig('graphics/messung_6_1.png')
