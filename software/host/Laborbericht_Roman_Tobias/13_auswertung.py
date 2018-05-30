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
messung_1 = [[229, 327, 427, 528,625,725,826,925,1027],[0, 9.00E-05,1.63E-04,2.36E-04,3.33E-04,4.07E-04,4.74E-04,5.57E-04,6.51E-04]]		# Weglänge Spannung
#  messung_2 = [[0.04, 0.08, 0.12],[3.36, 2.13, 1.185]]	# Weglänge Spannung

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

def f_messung_1(x, u, p):
	return p * exp(-u*x)




#  # Achtung als Startwert nicht null einsetzen p0=[1,1,1,1,ect]
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

fig = plot.figure(figsize=(10,5))
fig.suptitle('Amplitudendaempfung 5MHz Longitudinal', fontsize=LABEL_S)
plot.xlabel('Weglaenge [m]',fontsize=LABEL_S)
plot.ylabel('Spannung [V]',fontsize=LABEL_S)
plot.minorticks_on()
plot.grid()

#-----------------------------------------------------------------------------#
plot.plot(messung_1[0], messung_1[1], '.', label='PMMA', color='blue')
plot.plot(t_messung_1_fit, y_messung_1_fit, '-', color='blue')

----------------#

plot.legend(fontsize=LABEL_S)
#plot.show()
fig.savefig('graphics/messung_1.png')
