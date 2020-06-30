import numpy as np
import matplotlib.pyplot as plt

class World(object):
	def __init__(self, shape, random=True, dtype=np.int8):
		self.data = np.random.randint(0, 2, size=shape, dtype=dtype)
		self.shape = self.data.shape
		self.dtype = dtype
		self._engine = Engine(self)
		self.step = 0
	def generation(self):
		self.step += 1
		self._engine.next_state()
		yield self.data         

class Engine(object):
    def __init__(self, world, dtype=np.int8):
        self._world = world
        self.dtype=dtype
        self.shape = world.shape
        self.neighbor = np.zeros(world.shape, dtype=dtype)
        self._neighbor_id = self._make_neighbor_indices()
    def _make_neighbor_indices(self):
        d = [slice(None), slice(1, None), slice(0, -1)]
        d2 = [
            (0, 1), (1, 1), (1, 0), (1, -1)
        ]
        out = [None for i in range(8)]
        for i, idx in enumerate(d2):
            x, y = idx
            out[i] = [d[x], d[y]]
            out[7 - i] = [d[-x], d[-y]]
        return out
    def _count_neighbors(self):
        self.neighbor[:, :] = 0
        w = self._world.data
        n_id = self._neighbor_id
        n = self.neighbor
        for i in range(8):
            n[n_id[i]] += w[n_id[7 - i]]
    def _update_world(self):
        self._world.data&=(self.neighbor==2)
        self._world.data|=(self.neighbor==3)
    def next_state(self):
        self._count_neighbors()
        self._update_world()

def main():
	stack=[]
	world = World((500, 500))
	plt.ion()
	fig=plt.figure(figsize=(8, 5))
	while True:
		for w in world.generation():
			if len(stack)<3: stack.append(w)
			elif len(stack)==3:
				new=np.zeros(world.shape, dtype=np.int8)
				unos=np.zeros(world.shape, dtype=np.int8)
				ceros=np.zeros(world.shape, dtype=np.int8)
				c=1
				for i in stack:
					unos+=(i == 1).astype(int)
					ceros+=(i == 0).astype(int)
					fig.add_subplot(1, 4, c)
					c+=1
					plt.imshow(i)
				new[unos-ceros > 0] = 1
				stack.pop(0)
				world.data=new
				fig.add_subplot(1, 4, 4)
				plt.imshow(world.data)
				plt.pause(0.01)


if __name__ == '__main__':
    main()