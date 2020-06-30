import itertools
import numpy as np

def array2text(array):
	txt=''
	for i in range(len(array)):
		txt+='{'
		for j in range(len(array[i])):
			txt+=str(array[i][j])
			if j != len(array[i])-1: txt+=','
		txt+='}'
		if i!=len(array)-1: txt+=','
	return txt
def juego(array, reglas=(2,3,3,3)):
	for x in range(array.shape[0]):
		for y in range(array.shape[1]):
			n=n_vivos(array, x,y)
			if array[y][x] and n == reglas[0] or n==reglas[1]: array[x][y]=1
			elif not array[y][x] and n == reglas[2] or n==reglas[3]: array[x][y]=1
			else: array[y][x]=0
	return array

def n_vivos(array,x,y):
	c=0
	try:
		if array[y+1][x+1]: c+=1
	except: None
	try:
		if array[y+1][x]: c+=1
	except: None
	try:
		if array[y+1][x-1]: c+=1
	except: None
	try:
		if array[y][x-1]: c+=1
	except: None
	try:
		if array[y-1][x-1]: c+=1
	except: None
	try:
		if array[y-1][x]: c+=1
	except: None
	try:
		if array[y-1][x+1]: c+=1
	except: None
	try:
		if array[y][x+1]: c+=1
	except: None
	return c

for i in range(2,5):
	lst = list(itertools.product([0, 1], repeat=i*i))
	array=[]
	for j in lst:
		f=open(str(i)+'x'+str(i)+'.txt','a')
		gen=0
		array=np.array(j)
		array=np.reshape(array,(-1,i))
		org=array
		while gen<=5:
			array=juego(array)			
			f.write('AdjacencyGraph[{'+array2text(array)+'}]\n')
			gen+=1
		gen=0
		array=org
		while gen<=5:
			array=juego(array,(7,7,2,2))
			f.write('AdjacencyGraph[{'+array2text(array)+'}]\n')
			gen+=1
		f.close()
		