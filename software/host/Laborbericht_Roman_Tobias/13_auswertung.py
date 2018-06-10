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

messung_1 = (readarray("13_messung_1.txt").transpose())
messung_2 = (readarray("13_messung_2.txt").transpose())

################################################################################
# Variablen ueber alle Messungen
################################################################################
LABEL_S = 17

################################################################################
# Auswertung Aufgabe 1
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 1')
print('================================================================================')

f1 = 1
f2 = 4.82
s1 = 0.12
s2 = 4.75

def f_messung_1(f, c, x):
	return 4*2*pi*f*((f1 * (s2+f2))/(c)) + x




popt, pcov = curve_fit(f_messung_1, messung_1[0], messung_1[1],p0=[1,1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))

t_messung_1_fit = linspace(min(messung_1[0]), max(messung_1[0]), 5000)
y_messung_1_fit = f_messung_1(t_messung_1_fit, popt[0], popt[1])

################################################################################
# Figur plotten
################################################################################


plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,6))
fig.suptitle('Lichtgeschwindigkeit Messung 1', fontsize=LABEL_S)
plot.ylabel('x [m]',fontsize=LABEL_S)
plot.xlabel('Frequenz [Hz]',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

#-----------------------------------------------------------------------------#
plot.plot(messung_1[0], messung_1[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_1_fit, y_messung_1_fit, '-', label='Fit', color='black')

#-----------------------------------------------------------------------------#

plot.legend(fontsize=LABEL_S)
#plot.show()
fig.savefig('graphics/messung_1.png')

################################################################################
# Auswertung Aufgabe 2
################################################################################
print('================================================================================')
print('Auswertung Aufgabe 2')
print('================================================================================')

f1 = 1
f2 = 4.82
s1 = 0.12
s2 = 4.75

def f_messung_1(f, c, x):
	return 4*2*pi*f*((f1 * (s2+f2))/(c)) + x


popt, pcov = curve_fit(f_messung_1, messung_2[0], messung_2[1],p0=[1,1])

print('Fitwerte' + str(popt))
print('Fehlergrenze' + str(sqrt(diag(pcov))))

t_messung_2_fit = linspace(min(messung_2[0]), max(messung_2[0]), 5000)
y_messung_2_fit = f_messung_1(t_messung_2_fit, popt[0], popt[1])

################################################################################
# Figur plotten
################################################################################


plot.rc('xtick',labelsize=LABEL_S)
plot.rc('ytick',labelsize=LABEL_S)
plot.rc('text', usetex=True)
plot.rc('font', family='serif')

fig = plot.figure(figsize=(10,6))
fig.suptitle('Lichtgeschwindigkeit Messung 2', fontsize=LABEL_S)
plot.ylabel('x [m]',fontsize=LABEL_S)
plot.xlabel('Frequenz [Hz]',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

#-----------------------------------------------------------------------------#
plot.plot(messung_2[0], messung_2[1], '.', label='Messwerte', color='blue')
plot.plot(t_messung_2_fit, y_messung_2_fit, '-', label='Fit', color='black')

#-----------------------------------------------------------------------------#

plot.legend(fontsize=LABEL_S)
#plot.show()
fig.savefig('graphics/messung_2.png')
