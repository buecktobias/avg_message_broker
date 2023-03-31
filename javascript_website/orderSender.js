const http = require('http');
const uuid = require('uuid'); // Importieren des uuid-Moduls

export default class OrderSender {
    credentials = 'admin:admin';

    createHardwareOrderObject() {
        return {
            BestellungsId: uuid.v4(),
            BestellArt: "HardwareOrder",
            Datum: new Date().toISOString(),
            Stueckzahl: Math.floor(Math.random() * 20),
            ArtikelId: Math.floor(Math.random() * 20),
            KundenId: 260,

        };
    }

    createSoftwareOrderObject() {
        return {
            BestellungsId: uuid.v4(),
            BestellArt: "SoftwareOrder",
            LizenzArt: "BASIC",
            Datum: new Date().toISOString(),
            KundenId: 260,
        };
    }

    sendPostRequest(payload, path) {
        const httpRequestOptions = {
            hostname: 'activemq', port: 8161, path: path, method: 'POST', auth: this.credentials,
            headers: {
                'Content-Type': 'application/json', 'Content-Length': JSON.stringify(payload).length
            }
        };

        const activeMQHTTPRequest = http.request(httpRequestOptions, (res) => {

            res.on('data', (d) => {
                process.stdout.write(d);
            });
        });

        activeMQHTTPRequest.on('error', (error) => {
            console.error(error);
        });

        activeMQHTTPRequest.write(JSON.stringify(payload));
        activeMQHTTPRequest.end();
    }

    sendSoftwareOrder() {
        console.log("SoftwareOrder wird gesendet...");
        this.sendPostRequest(this.createSoftwareOrderObject(), '/api/message/SoftwareOrders?type=queue');
    }

    sendHardwareOrder() {
        console.log("HardwareOrder wird gesendet...");
        this.sendPostRequest(this.createHardwareOrderObject(), '/api/message/HardwareOrders?type=queue');
    }
}