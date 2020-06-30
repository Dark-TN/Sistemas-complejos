import time
from Tkinter import *
import tkMessageBox

def leer(e1,root):
	global lista
	global comp
	global numero
	global cv
	global n
	global t
	global lo
	global w
	global cab
	cinta=[]
	if e1.get()=='':
		tkMessageBox.showinfo("Error", "Inserta los valores correctamente.",parent=root)
		return
	else:
		for i in e1.get():
			if i!='1':
				tkMessageBox.showinfo("Error", "Inserta la lista correctamente.",parent=root)
				return
	w=5
	for i in range(20): w+=55
	cv.configure(width=w,height=300)
	cv.delete('all')
	cx=(root.winfo_screenwidth() // 2)-(w // 2)
	cy=(root.winfo_screenheight() // 2)-(250 // 2)
	root.geometry('%dx250+%d+%d' %(w,cx,cy))
	x=5
	for i in range(20):
		cinta.append('b')
		cuadros.append(cv.create_rectangle(x,95,x+50,145,fill="#F2F3F4",outline='#F2F3F4'))
		posx_cuadros.append((x,x+50))
		valores.append(cv.create_text(x+25,120,text='b'))
		x+=55
	c=m=(20-len(e1.get()))/2
	for i in e1.get():
		cinta[m]=str(i)
		cv.itemconfig(valores[m], text='1')
		m+=1
	cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")#posicion del cabezal
	lo=cv.create_text(w/2,190,text='Resultado: _ Estado: p')
	comp= cv.create_text(w/2,210,text='')
	cv.update()
	time.sleep(1)
	duplicador_unos(cinta, cuadros, valores, c)	
def borrar_blanco(cinta):
	cadena=[]
	for i in cinta: 
		if i != 'b': cadena.append(i)
	return ''.join(cadena)
def duplicador_unos(cinta,cuadros,valores,c):
	global lo
	global cab
	estado='p'
	while estado!='s':
		if estado=='p':
			if cinta[c]=='1':
				estado='q'
				cv.itemconfig(lo,text='Resultado: _ Estado: q')
				cv.itemconfig(valores[c], text='0')
				cinta[c]='0'
				c+=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
			elif cinta[c]=='0':
				c-=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
			elif cinta[c]=='b':
				estado='r'
				c+=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
		elif estado=='q':
			if cinta[c]=='1':
				c+=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
			elif cinta[c]=='0':
				c+=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
			elif cinta[c]=='b':
				estado='p'
				cv.itemconfig(lo,text='Resultado: _ Estado: p')
				cinta[c]='0'
				cv.itemconfig(valores[c], text='0')
				c-=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
		elif estado=='r':
			if cinta[c]=='0':
				cinta[c]='1'
				cv.itemconfig(valores[c], text='1')
				c+=1
				cv.delete(cab)
				cab=cv.create_polygon((posx_cuadros[c][0], 55, posx_cuadros[c][1], 55, (posx_cuadros[c][1])-27, 80), fill="black")
				cv.update()
				time.sleep(1)
			elif cinta[c]=='b':
				estado='s'
				cv.itemconfig(lo,text='Resultado: %s Estado: s' %borrar_blanco(cinta))
				cv.update()
				time.sleep(1)
def inicio():
	cv.configure(width=300,height=95)
	cv.delete('all')
	h1=cv.create_text(85,15,text='Inserta la cadena de unos (1): ')
	e1 = Entry(cv)
	cv.create_window(225,15,window=e1)
	b1 = Button(cv, text = "Buscar",command=lambda: leer(e1,root))
	b1_window = cv.create_window(150, 75, window=b1)
	cx=(root.winfo_screenwidth() // 2)-(300 // 2)
	cy=(root.winfo_screenheight() // 2)-(95 // 2)
	root.geometry('300x95+%d+%d' %(cx,cy))
	cv.update()
#Canvas   
global i
global n
global t
global l
global lista
global numero
global cv
global cuadros
global valores
global posx_cuadros
global w
global comp
global cx
global cy
i=0     
cuadros=[]
valores=[]
posx_cuadros=[]     
root = Tk()
root.title("Busqueda binaria")
cv = Canvas(width=300, height=95, bg='white')
cv.pack()
cx=(root.winfo_screenwidth() // 2)-(300 // 2)
cy=(root.winfo_screenheight() // 2)-(95 // 2)
inicio()
root.geometry('300x95+%d+%d' %(cx,cy))
root.mainloop()
