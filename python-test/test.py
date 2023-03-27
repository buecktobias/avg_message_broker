import requests

# Set the URL, credentials, and parameters
url = "http://localhost:8161/api/message/SoftwareOrders"
params = {"type": "queue", "clientId": "1234"}
auth = ("admin", "admin")

# Send the GET request and print the response body
response = requests.get(url, params=params, auth=auth)
print(response.text)
#print(response.headers)