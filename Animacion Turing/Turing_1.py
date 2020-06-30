global tamano_cinta
tamano_cinta=100
def borrar_blanco(cinta):
	cadena=[]
	for i in cinta: 
		if i != 'b': cadena.append(i)
	return ''.join(cadena)
def duplicador_unos(palabra):
	estado='p'
	cinta=[]
	for i in range(tamano_cinta): cinta.append('b')
	for i in palabra: 
		if i != '1':
			return 'error'
	c=m=(tamano_cinta-len(palabra))/2
	for i in palabra:
		cinta[m]=i
		m+=1
	while estado!='s':
		if estado=='p':
			if cinta[c]=='1':
				estado='q'
				cinta[c]='0'
				c+=1
			elif cinta[c]=='0':
				c-=1
			elif cinta[c]=='b':
				estado='r'
				c+=1
		elif estado=='q':
			if cinta[c]=='1':
				c+=1
			elif cinta[c]=='0':
				c+=1
			elif cinta[c]=='b':
				estado='p'
				cinta[c]='0'
				c-=1
		elif estado=='r':
			if cinta[c]=='0':
				cinta[c]='1'
				c+=1
			elif cinta[c]=='b':
				estado='s'
		print borrar_blanco(cinta)
	return borrar_blanco(cinta)
duplicador_unos('1111')