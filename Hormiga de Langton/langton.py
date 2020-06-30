#!/usr/bin/python
import numpy as np
import matplotlib.pyplot as plt
import matplotlib as mpl
import itertools
import tkinter as Tk
	
class GUI:
	def start(self):
		self.grid = Grid(int(self.e5.get()),int(self.e6.get()))
		self.grid.random(float(self.e4.get()),float(self.e1.get()),float(self.e2.get()),float(self.e3.get()))
		while(True):
			self.grid.animar()
			self.grid.update()
	def centerWindow(self):
		w=305
		h=180
		ws = self.master.winfo_screenwidth()
		hs = self.master.winfo_screenheight()
		x = (ws/2) - (w/2)    
		y = (hs/2) - (h/2)
		self.master.geometry('%dx%d+%d+%d' % (w, h, x, y))
	def __init__(self):
		self.grid=None
		self.master = Tk.Tk()
		self.master.resizable(0, 0)
		self.master.title("Hormiga de Langton")
		self.centerWindow()
		Tk.Label(self.master, text="Probabilidad hormiga obrera: ").grid(row=0, sticky=Tk.W, pady=1,padx=1)
		Tk.Label(self.master, text="Probabilidad hormiga princesa: ").grid(row=1, sticky=Tk.W, pady=1,padx=1)
		Tk.Label(self.master, text="Probabilidad hormiga reina: ").grid(row=2,sticky=Tk.W, pady=1,padx=1)
		Tk.Label(self.master, text="Semilla: ").grid(row=3,sticky=Tk.W, pady=1,padx=1)
		Tk.Label(self.master, text="Ancho: ").grid(row=4,sticky=Tk.W, pady=1,padx=1)
		Tk.Label(self.master, text="Largo: ").grid(row=5,sticky=Tk.W, pady=1,padx=1)
		self.e1 = Tk.Entry(self.master)
		self.e2 = Tk.Entry(self.master)
		self.e3 = Tk.Entry(self.master)
		self.e4 = Tk.Entry(self.master)
		self.e5 = Tk.Entry(self.master)
		self.e6 = Tk.Entry(self.master)
		self.e1.grid(row=0, column=1, pady=1,padx=1)
		self.e2.grid(row=1, column=1, pady=1,padx=1)
		self.e3.grid(row=2, column=1, pady=1,padx=1)
		self.e4.grid(row=3, column=1, pady=1,padx=1)
		self.e5.grid(row=4, column=1, pady=1,padx=1)
		self.e6.grid(row=5, column=1, pady=1,padx=1)
		Tk.Button(self.master, text='Iniciar', command= self.start).grid(row=6, column=0, pady=4,padx=4)
		self.master.mainloop()
		
class Graph:
	def data(self,datao,datap,datar,datax):
		self.a.plot(datax,datao,color='blue',label='Obreras')
		self.a.plot(datax,datap,color='red',label='Princesas')
		self.a.plot(datax,datar,color='yellow',label='Reinas')
	def __init__(self):
		self.f = plt.figure()
		self.a = self.f.add_subplot(111)
		self.a.set_xlabel('Generacion')
		self.a.set_ylabel('Hormigas vivas')	
		
class Grid:
	def random(self, prob=0.01, probo=80, probp=14, probr=6):
		for i in range(int(prob/100.0*self.dimen[0]*self.dimen[1])):
			self.ants.append(Ant(np.random.randint(0,5), np.random.randint(0,self.dimen[0]), np.random.randint(0,self.dimen[1]), np.random.choice(np.arange(0, 3), p=[probo/100.0,probp/100.0,probr/100.0]), self))
	def populate(self):
		for a,b in itertools.combinations(self.ants,2):
			if a.position == b.position:
				if a.type == 1 and b.type == 2 or a.type == 2 and b.type == 1:
					self.ants.append(Ant(np.random.randint(0,5), a.position[0], a.position[1], np.random.choice(np.arange(0, 3), p=[0.8,0.14,0.06]), self))
	def update(self):
		for ant in self.ants:
			if ant.vida:
				ant.move()
			else: del self.ants[self.ants.index(ant)]
			try:
				self.image[ant.position[0], ant.position[1]] = self.colores[2]
			except: continue
			if ant.type==0: self.obreras+=1
			elif ant.type==1: self.princesas+=1
			elif ant.type==2: self.reinas+=1
		self.plot_data_o.append(self.obreras)
		self.plot_data_p.append(self.princesas)
		self.plot_data_r.append(self.reinas)
		self.plot_data_x.append(self.gen)
		self.gen+=1
		self.populate()
	def animar(self):
		if not self.gen:
			self.im=self.ax.imshow(self.image,vmin=0,vmax=2)
			self.ax2.plot(self.plot_data_x,self.plot_data_o,color='blue',label='Obreras')
			self.ax2.plot(self.plot_data_x,self.plot_data_p,color='red',label='Princesas')
			self.ax2.plot(self.plot_data_x,self.plot_data_r,color='yellow',label='Reinas')
			self.asp = np.diff(self.ax2.get_xlim())[0] / np.diff(self.ax2.get_ylim())[0]
			self.ax2.set_aspect(self.asp)
			self.ax2.legend(['Obreras', 'Princesas', 'Reinas'], loc='upper left')
		else:
			self.im.set_data(self.image)
			self.ax2.plot(self.plot_data_x,self.plot_data_o,color='blue',label='Obreras')
			self.ax2.plot(self.plot_data_x,self.plot_data_p,color='red',label='Princesas')
			self.ax2.plot(self.plot_data_x,self.plot_data_r,color='yellow',label='Reinas')
			self.asp = np.diff(self.ax2.get_xlim())[0] / np.diff(self.ax2.get_ylim())[0]
			self.ax2.set_aspect(self.asp)
		plt.pause(0.01)
	def __init__(self, dimen_x=1000, dimen_y=1000):
		self.dimen = (dimen_x, dimen_y)
		self.board = np.zeros((self.dimen[0], self.dimen[1]), dtype=np.uint8)
		self.image = np.zeros((self.dimen[0], self.dimen[1], 3), dtype=np.uint8)
		self.ants=[]
		self.plot_data_o=[]
		self.plot_data_p=[]
		self.plot_data_r=[]
		self.plot_data_x=[]
		self.gen=0
		self.fig, (self.ax, self.ax2) = plt.subplots(ncols=2,figsize=(13,5))
		self.ax2.set_title('Grafica')
		self.ax2.set_xlabel('Generacion')
		self.ax2.set_ylabel('Hormigas vivas')
		self.im=None
		self.colores=[[0,0,0],[255,255,255],[255,0,0]]
		self.obreras=0
		self.princesas=0
		self.reinas=0

class Ant:
	def __init__(self, face_direction, xpos, ypos, type, grid, rule='rl'):
		self.face_direction = face_direction
		self.position = [xpos, ypos]
		self.rule = rule
		self.possiblefacings = ((1, 0), (0, -1), (-1, 0), (0, 1))
		self.nstates=len(rule)
		self.grid=grid
		self.vida=2500
		self.type=type
	def move(self):
		try:
			self.cycle_dir(self.rule[self.grid.board[self.position[0], self.position[1]]])
			self.grid.board[self.position[0], self.position[1]] = (self.grid.board[self.position[0], self.position[1]] + 1) % self.nstates
			self.grid.image[self.position[0], self.position[1]] = self.grid.colores[(self.grid.board[self.position[0], self.position[1]] + 1) % self.nstates]
			self.vida-=1
			self.position[0] = self.position[0] + self.possiblefacings[self.face_direction][0]
			self.position[1] = self.position[1] + self.possiblefacings[self.face_direction][1]
		except: None
	def cycle_dir(self, directive):
		if directive == 'r': self.face_direction = (self.face_direction - 1) % len(self.possiblefacings)
		elif directive == 'l': self.face_direction = (self.face_direction + 1) % len(self.possiblefacings)
		elif directive == 'u': self.face_direction = (self.face_direction + 2) % len(self.possiblefacings)
		elif directive == 'n': self.face_direction = self.face_direction

def main():
	gui=GUI()		
		
if __name__ == "__main__":
    main()