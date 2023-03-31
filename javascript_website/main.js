const http = require('http');
const uuid = require('uuid'); // Importieren des uuid-Moduls
const credentials = 'admin:admin';

function start(){
  console.log("javascript website!");
  setTimeout(start, 30_000)
}

function sendPostRequest(payload, isHardware) {
  let path;
  if (isHardware) {
    path = '/api/message/HardwareOrders?type=queue';
  } else {
    path = '/api/message/SoftwareOrders?type=queue';
  }
  const options = {
    hostname: 'activemq',
    port: 8161,
    path: path,
    method: 'POST',
    auth: credentials, // Hinzufügen der Authentifizierungsdaten
    headers: {
      'Content-Type': 'application/json',
      'Content-Length': JSON.stringify(payload).length
    }
  };

  const req = http.request(options, (res) => {
    console.log(`statusCode: ${res.statusCode}`);

    res.on('data', (d) => {
      process.stdout.write(d);
    });
  });

  req.on('error', (error) => {
    console.error(error);
  });

  req.write(JSON.stringify(payload));
  req.end();
}

function SoftwareBestellung() {
  console.log("SoftwareBestellung wird gesendet...");
  const date = new Date();
  const timestamp = date.toISOString();
  const data = {
    BestellID: uuid.v4(),
    ArtikelID: Math.floor(Math.random() * 20),
    Anzahl: Math.floor(Math.random() * 20),
    KundenID: '420',
    Datum: timestamp,
  };

  sendPostRequest(data, false);
}

function HardwareBestellung() {
  console.log("HardwareBestellung wird gesendet...");

  const date = new Date();
  const timestamp = date.toISOString();
  const data = {
    BestellID: uuid.v4(),
    ArtikelID: Math.floor(Math.random() * 20),
    Anzahl: Math.floor(Math.random() * 20),
    KundenID: '260',
    Datum: timestamp,
  };

  sendPostRequest(data, true);
}

const args = process.argv.slice(2);

if (args[0] === "SoftwareBestellung") {
  SoftwareBestellung();
} else if (args[0] === "HardwareBestellung") {
  HardwareBestellung();
} else  if(args[0] === "start"){
  start();
}
