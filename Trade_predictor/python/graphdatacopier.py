import requests
from datetime import date

url = "https://groww.in/v1/api/charting_service/v2/chart/delayed/exchange/NSE/segment/CASH/TATAMOTORS/daily?intervalInMinutes=1&minimal=true"
#url ="https://groww.in/v1/api/charting_service/v2/chart/delayed/exchange/NSE/segment/CASH/TATAMOTORS/weekly?intervalInMinutes=1&minimal=true"
# url="https://groww.in/v1/api/charting_service/v2/chart/delayed/exchange/NSE/segment/CASH/TATAMOTORS/monthly?intervalInMinutes=1&minimal=true"
# Send a GET request to the URL
response = requests.get(url)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    # Get the current date
    current_date = date.today().strftime("%Y-%m-%d")

    # Create the file name with the current date appended
    file_name = f"tata_motors_{current_date}.txt"

    # Open the file in append mode and write the response content to it
    with open(file_name, "a") as file:
        file.write(response.text)
        print(f"Data appended to {file_name} file.")
else:
    print("Error occurred while fetching data:", response.status_code)
