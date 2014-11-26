import numpy as np
import matplotlib.pyplot as plt

with open("total.txt") as f:
    x_axis = f.read()
x_axis = x_axis.split('\n')
x_axis = [row.split(' ')[0] for row in x_axis]


with open("output.txt") as f:
    y_axis1 = f.read()
y_axis1 = y_axis1.split('\n')
y_axis1 = [row.split(' ')[0] for row in y_axis1]

with open("output1.txt") as f:
    y_axis2 = f.read()
y_axis2 = y_axis2.split('\n')
y_axis2 = [row.split(' ')[0] for row in y_axis2]

with open("output2.txt") as f:
    y_axis3 = f.read()
y_axis3 = y_axis3.split('\n')
y_axis3 = [row.split(' ')[0] for row in y_axis3]


fig = plt.figure()

ax1 = fig.add_subplot(111)

ax1.set_title("Plot title...")    
ax1.set_xlabel('your x label..')
ax1.set_ylabel('your y label...')

ax1.plot(x_axis,y_axis1, c='r', label='the data')

leg = ax1.legend()

plt.show()