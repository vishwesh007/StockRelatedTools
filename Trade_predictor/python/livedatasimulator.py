import time
import json

import numpy as np
from sklearn.linear_model import LinearRegression
# Open the file containing the data
with open("tata_motors_2023-06-17.txt", "r") as file:
    # Load the data from the file
    data = json.load(file)

# # Extract the candles from the loaded data
# candles = data["candles"]

# # Iterate through the candles and print each value every second
# for candle in candles:
#     value = candle[1]
#     print(value)
#     time.sleep(1)



# Extract the candle values from the loaded data
candles = data["candles"]

# Split the data into input (X) and output (y) variables
X = np.array([candle[0] for candle in candles]).reshape(-1, 1)
y = np.array([candle[1] for candle in candles])

# Create and fit the linear regression model
model = LinearRegression()
model.fit(X, y)

# Predict the next candle value
next_candle_time = candles[-1][0] + 60  # Assuming each candle has a time difference of 60 seconds
next_candle_value = model.predict(np.array([[next_candle_time]]))
print("Predicted next candle value:", next_candle_value[0])

