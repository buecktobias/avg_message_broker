function start(){
    console.log("software order started!");
    setTimeout(start, 30_000)
}
const http = require('http');
const credentials = 'admin:admin';

function sendGetRequest() {
    const options = {
        hostname: 'activemq',
        port: 8161,
        path: '/api/message/SoftwareOrders?type=queue&oneShot=true',
        method: 'GET',
        headers: {
            Authorization: 'Basic ' + Buffer.from(credentials).toString('base64')
        }
    };

    const req = http.request(options, (res) => {

        res.on('data', (d) => {
            if(res.statusCode === 200){
                console.log("Received SoftwareOrder: " + d)
            }
        });
    });

    req.on('error', (error) => {
        console.error(error);
    });

    req.end();
}

function listenToNewGetRequests(){
    sendGetRequest()
    setTimeout(listenToNewGetRequests, 100)
}


const args = process.argv.slice(2);


if (args[0] === "listen") {
    listenToNewGetRequests()
}else{
    start();
}