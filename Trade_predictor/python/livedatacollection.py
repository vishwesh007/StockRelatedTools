from kite_trade import KiteApp
import datetime
import time
file_path="enctoken.txt"

with open(file_path,'r') as file:
    enctoken=file.readline
#enctoken = "t1kTwWfFsedTSpb2GXLHG8hPDmqVfaxKr523GXrsYhaTHXTA2fpA2kxOvr6kOGcUr/ogESBUqAF18SH7ZoJkeRUdrZFpcEaVCi4ehBSHVTcXW77wxYPeHg=="
 
kite = KiteApp(enctoken=enctoken)
 
input_file_path = "C:\\Users\\Vishw\\StockRelatedTools\\Trade_predictor\\python\\nse_values.txt"
output_file_path = "C:\\Users\\Vishw\\StockRelatedTools\\Trade_predictor\\python\\output.txt"
 
# Read the NSE values from the file
with open(input_file_path, 'r') as input_file:
    nse_values = input_file.read().splitlines()
 
end_time = datetime.datetime.now().replace(hour=15, minute=00, second=0, microsecond=0)
 
# Open the output file in append mode
output_file = open(output_file_path, 'a')
 
while datetime.datetime.now() < end_time:
    # Get the LTP (Last Traded Price) for the NSE values using kite.ltp()
    ltp_values = kite.ltp(nse_values)
 
    # Write the LTP values to the output file
    output_file.write(f'{ltp_values}\n')
    output_file.flush()  # Flush the buffer to ensure immediate write
 
    # Print the LTP values
    print(ltp_values)
 
    time.sleep(1)
 
# Close the output file
output_file.close()