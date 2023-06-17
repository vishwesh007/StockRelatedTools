from kite_trade import *
import datetime
import time

enctoken = "t1kTwWfFsedTSpb2GXLHG8hPDmqVfaxKr523GXrsYhaTHXTA2fpA2kxOvr6kOGcUr/ogESBUqAF18SH7ZoJkeRUdrZFpcEaVCi4ehBSHVTcXW77wxYPeHg=="

kite = KiteApp(enctoken=enctoken)

input_file_path = "C:\\Users\\Vishwesh\\StockRelatedTools\\Trade_predictor\\python\\nse_values.txt"
output_file_path = "C:\\Users\\Vishwesh\\StockRelatedTools\\Trade_predictor\\python\\output.txt"

# Read the NSE values from the file
with open(input_file_path, 'r') as input_file:
    nse_values = input_file.read().splitlines()

end_time = datetime.datetime.now().replace(hour=15, minute=00, second=0, microsecond=0)

# Open the output file in append mode
output_file = open(output_file_path, 'a')

minute_start_time = datetime.datetime.now()
minute_ltp_values = []
minute_average = 0
opening_price = 0

while datetime.datetime.now() < end_time:
    current_time = datetime.datetime.now()

    # Get the LTP (Last Traded Price) for the NSE values using kite.ltp()
    ltp_values = kite.ltp(nse_values)

    # Set the opening price if it's not already set
    if opening_price == 0:
        opening_price = ltp_values[0]

    # Calculate the average for the minute
    minute_ltp_values.extend(ltp_values)  # Add the LTP values to the minute list
    minute_average = sum(minute_ltp_values) / len(minute_ltp_values)  # Calculate the average

    # Check for a bullish or bearish alert based on high spike
    current_value = ltp_values[-1]
    spike_threshold = 0.03 * opening_price  # 3% threshold
    if current_value > opening_price + spike_threshold:
        print("Bullish Alert!")
    elif current_value < opening_price - spike_threshold:
        print("Bearish Alert!")

    # Print the current LTP values, average for the minute, and opening price
    print(f"Current LTP Values: {ltp_values}")
    print(f"Minute Average: {minute_average}")
    print(f"Opening Price: {opening_price}")

    # Check if a minute has passed
    if current_time >= minute_start_time + datetime.timedelta(minutes=1):
        # Write the end price and average price to the output file
        output_file.write(f"End Price: {ltp_values[-1]}\n")
        output_file.write(f"Average Price: {minute_average}\n")
        output_file.flush()  # Flush the buffer to ensure immediate write

        # Reset the minute start time and clear the minute LTP values
        minute_start_time = datetime.datetime.now()
        minute_ltp_values = []

    time.sleep(1)

# Close the output file
output_file.close()
