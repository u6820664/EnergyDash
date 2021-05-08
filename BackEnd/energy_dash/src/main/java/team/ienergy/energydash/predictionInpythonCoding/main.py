import pandas as pd
import torch
import torch.nn.functional as F
from sklearn.model_selection import train_test_split
import sklearn
import numpy as np
from matplotlib import pyplot as plt

# load data
data = pd.read_csv('data.csv')

# Set the column name for the first column
data.rename(columns={'Unnamed: 0': 'ID'}, inplace=True)
# Remove the first String (O and T)
data['datetime'] = data['datetime'].str.slice(0, 7)
# Drop the useless column
data = data.drop(columns="ID")
data = data.drop(columns="pid")
data = data.drop(columns="controlled")
data = data.drop(columns="export")

# normalize data and transfer to num type
data['datetime'] = data.loc[:, 'datetime'].apply(lambda x: x.replace('-', ''))

data = data.apply(pd.to_numeric)

# provide the sum of the one month's electricity supply
dataPre = []
dataMonth = []
dateTime = 0
sumForOneMonth = 0
for index, date in data.iterrows():
    if date['datetime'] > 202200:
        break
    if date['datetime'] > 201200:
        if dateTime == int(date['datetime'] + 0.000000001):
            sumForOneMonth += date['supply']
        else:
            dataMonth.append(dateTime)
            dataMonth.append(sumForOneMonth)
            dataPre.append(dataMonth)
            dataMonth = []
            sumForOneDay = date['supply']
            dateTime = int(date['datetime'] + 0.000000001)
dataPre.remove([0, 0])

# Build an empty DataFrame and store all the normalized data
data = pd.DataFrame(
    columns=('Year', 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October',
             'November', 'December'))

line = {'Year': 1, 'January': 0.1, 'February': 0.1, 'March': 0.1, 'April': 0.1, 'May': 0.1, 'June': 0.1, 'July': 0.1,
        'August': 0.1, 'September': 0.1, 'October': 0.1, 'November': 0.1, 'December': 0.1}
year = 0
for month in dataPre:
    if year == int(month[0] / 100):
        if month[0] % 100 == 1:
            line['January'] = month[1]
            continue
        if month[0] % 100 == 2:
            line['February'] = month[1]
            continue
        if month[0] % 100 == 3:
            line['March'] = month[1]
            continue
        if month[0] % 100 == 4:
            line['April'] = month[1]
            continue
        if month[0] % 100 == 5:
            line['May'] = month[1]
            continue
        if month[0] % 100 == 6:
            line['June'] = month[1]
            continue
        if month[0] % 100 == 7:
            line['July'] = month[1]
            continue
        if month[0] % 100 == 8:
            line['August'] = month[1]
            continue
        if month[0] % 100 == 9:
            line['September'] = month[1]
            continue
        if month[0] % 100 == 10:
            line['October'] = month[1]
            continue
        if month[0] % 100 == 11:
            line['November'] = month[1]
            continue
        if month[0] % 100 == 12:
            line['December'] = month[1]
            continue
    else:
        data = data.append(line,ignore_index=True)
        line['Year'] = int(month[0] / 100)
        line['January']=month[1]
        year = int(month[0] / 100)

data=data[~data['Year'].isin([1])]


# put the data in input value and target value

X_data = pd.DataFrame(
    columns=('Year','January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October',
             'November', 'December'))
Y_data = pd.DataFrame(
    columns=('Year','January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October',
             'November', 'December'))

X_line={}
Y_line={}
boo=True
# keep the number of input values are equal to target value
for index, year in data.iterrows():
    if boo:
        X_line=year
        boo=False
    else:
        Y_line = year
        boo=True
    if boo:
        X_data=X_data.append(X_line,ignore_index=True)
        Y_data=Y_data.append(Y_line,ignore_index=True)

X_data = X_data.drop(columns="Year")
Y_data = Y_data.drop(columns="Year")

# collection
X_train_array = X_data.values
Y_train_array = Y_data.values

X = torch.tensor(X_train_array, dtype=torch.float)
Y = torch.tensor(Y_train_array, dtype=torch.float)



def sigmoid(x):
    # active function first layer to second layer
    return 1 / (1 + np.exp(-x))


def deriv_sigmoid(x):
    # gradient function first layer to second layer
    fx = sigmoid(x)
    return fx * (1 - fx)


def mse_loss(y_true, y_pred):
    # loss function
    return ((y_true - y_pred) ** 2).mean()


class OurNeuralNetwork:

    def __init__(self):
        # the parameter from first layer to second layer
        self.w11 = np.random.normal()
        self.w12 = np.random.normal()
        self.w13 = np.random.normal()
        self.w14 = np.random.normal()
        self.w15 = np.random.normal()
        self.w16 = np.random.normal()
        self.w17 = np.random.normal()
        self.w18 = np.random.normal()
        self.w19 = np.random.normal()
        self.w110 = np.random.normal()
        self.w111 = np.random.normal()
        self.w112 = np.random.normal()
        self.w21 = np.random.normal()
        self.w22 = np.random.normal()
        self.w23 = np.random.normal()
        self.w24 = np.random.normal()
        self.w25 = np.random.normal()
        self.w26 = np.random.normal()
        self.w27 = np.random.normal()
        self.w28 = np.random.normal()
        self.w29 = np.random.normal()
        self.w210 = np.random.normal()
        self.w211 = np.random.normal()
        self.w212 = np.random.normal()

        # parameters from second layers to third layer
        self.w1 = np.random.normal()
        self.w2 = np.random.normal()
        # Biases
        self.b1 = np.random.normal()
        self.b2 = np.random.normal()
        self.b3 = np.random.normal()

    def feedforward(self, x):

        # h1
        h1 = sigmoid(self.w11 * x[0] + self.w12 * x[1] + self.w13 * x[2] + self.w14 * x[3] + self.w15 * x[4] + self.w16 * x[5] + self.w17 * x[6] + self.w18 * x[7] + self.w19 * x[8] + self.w110 * x[9] + self.w111 * x[10] + self.w112 * x[11] + self.b1)
        # h2
        h2 = sigmoid(self.w21 * x[0] + self.w22 * x[1] + self.w23 * x[2] + self.w24 * x[3] + self.w25 * x[4] + self.w26 * x[5] + self.w27 * x[6] + self.w28 * x[7] + self.w29 * x[8] + self.w210 * x[9] + self.w211 * x[10] + self.w212 * x[11] + self.b2)
        o1 = self.w1 * h1 + self.w2 * h2 + self.b3
        return o1
        # training the model

    def train(self, data, all_y_trues):
        learn_rate = 0.05  # learning rate
        epochs = 1000  # the number of training
        # loss data
        self.loss = np.zeros(100)
        self.sum = 0
        # start to train
        for epoch in range(epochs):
            for x, y_true in zip(data, all_y_trues):
                # h1
                h1 = sigmoid(self.w11 * x[0] + self.w12 * x[1] + self.w13 * x[2] + self.w14 * x[3]+self.w15 * x[4]+self.w16 * x[5]+self.w17 * x[6]+self.w18 * x[7]+self.w19 * x[8]+self.w110 * x[9]+self.w111 * x[10]+self.w112 * x[11] + self.b1)
                # h2
                h2 = sigmoid(self.w21 * x[0] + self.w22 * x[1] + self.w23 * x[2] + self.w24 * x[3]+self.w25 * x[4]+self.w26 * x[5]+self.w27 * x[6]+self.w28 * x[7]+self.w29 * x[8]+self.w210 * x[9]+self.w211 * x[10]+self.w212 * x[11] + self.b2)
                # output point
                y_pred = self.w1 * h1 + self.w2 * h2 + self.b3
                # derivatives
                d_L_d_ypred = -2 * (y_true - y_pred)
                d_ypred_d_w1 = h1
                d_ypred_d_w2 = h2
                d_ypred_d_b3 = 0
                d_ypred_d_h1 = self.w1
                d_ypred_d_h2 = self.w2
                sum_1 = self.w11 * x[0] + self.w12 * x[1] + self.w13 * x[2] + self.w14 * x[3]+self.w15 * x[4]+self.w16 * x[5]+self.w17 * x[6]+self.w18 * x[7]+self.w19 * x[8]+self.w110 * x[9]+self.w111 * x[10]+self.w112 * x[11] + self.b1
                d_h1_d_w11 = x[0] * deriv_sigmoid(sum_1)
                d_h1_d_w12 = x[1] * deriv_sigmoid(sum_1)
                d_h1_d_w13 = x[2] * deriv_sigmoid(sum_1)
                d_h1_d_w14 = x[3] * deriv_sigmoid(sum_1)
                d_h1_d_w15 = x[4] * deriv_sigmoid(sum_1)
                d_h1_d_w16 = x[5] * deriv_sigmoid(sum_1)
                d_h1_d_w17 = x[6] * deriv_sigmoid(sum_1)
                d_h1_d_w18 = x[7] * deriv_sigmoid(sum_1)
                d_h1_d_w19 = x[8] * deriv_sigmoid(sum_1)
                d_h1_d_w110 = x[9] * deriv_sigmoid(sum_1)
                d_h1_d_w111 = x[10] * deriv_sigmoid(sum_1)
                d_h1_d_w112 = x[11] * deriv_sigmoid(sum_1)
                d_h1_d_b1 = deriv_sigmoid(sum_1)
                sum_2 = self.w21 * x[0] + self.w22 * x[1] + self.w23 * x[2] + self.w24 * x[3] + self.b2
                d_h1_d_w21 = x[0] * deriv_sigmoid(sum_2)
                d_h1_d_w22 = x[1] * deriv_sigmoid(sum_2)
                d_h1_d_w23 = x[2] * deriv_sigmoid(sum_2)
                d_h1_d_w24 = x[3] * deriv_sigmoid(sum_2)
                d_h1_d_w25 = x[4] * deriv_sigmoid(sum_2)
                d_h1_d_w26 = x[5] * deriv_sigmoid(sum_2)
                d_h1_d_w27 = x[6] * deriv_sigmoid(sum_2)
                d_h1_d_w28 = x[7] * deriv_sigmoid(sum_2)
                d_h1_d_w29 = x[8] * deriv_sigmoid(sum_2)
                d_h1_d_w210 = x[9] * deriv_sigmoid(sum_2)
                d_h1_d_w211 = x[10] * deriv_sigmoid(sum_2)
                d_h1_d_w212 = x[11] * deriv_sigmoid(sum_2)
                d_h1_d_b2 = deriv_sigmoid(sum_2)

                # gradient decent
                self.w11 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w11
                self.w12 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w12
                self.w13 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w13
                self.w14 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w14
                self.w15 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w15
                self.w16 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w16
                self.w17 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w17
                self.w18 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w18
                self.w19 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w19
                self.w110 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w110
                self.w111 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w111
                self.w112 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_w112
                self.b1 -= learn_rate * d_L_d_ypred * d_ypred_d_h1 * d_h1_d_b1

                self.w21 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w21
                self.w22 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w22
                self.w23 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w23
                self.w24 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w24
                self.w25 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w25
                self.w26 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w26
                self.w27 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w27
                self.w28 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w28
                self.w29 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w29
                self.w210 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w210
                self.w211 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w211
                self.w212 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_w212
                self.b2 -= learn_rate * d_L_d_ypred * d_ypred_d_h2 * d_h1_d_b2

                self.w1 -= learn_rate * d_L_d_ypred * d_ypred_d_w1
                self.w2 -= learn_rate * d_L_d_ypred * d_ypred_d_w2
                self.b3 -= learn_rate * d_L_d_ypred * d_ypred_d_b3

            if epoch % 10 == 0:
                y_preds = np.apply_along_axis(self.feedforward, 1, data)
                loss = mse_loss(all_y_trues, y_preds)
                print("Epoch %d loss: %.3f" % (epoch, loss))
                self.loss[self.sum] = loss
                self.sum = self.sum + 1




# input pre years' data
def inputDeal(line):
    inp=[]
    for value in line:
        inp.append(value)
    return inp
# training data
network = OurNeuralNetwork()
network.train(X, Y)
input = []
print(data)

input = inputDeal(data.iloc[7])[1:13]

a = network.feedforward(input)
print(a)

