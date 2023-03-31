import OrderSender from "./orderSender";

function start() {
  console.log("javascript website!");
  setTimeout(start, 30_000)
}

const args = process.argv.slice(2);
const orderSender = new OrderSender();
if (args[0] === "sendSoftwareOrder") {
  orderSender.sendSoftwareOrder();
} else if (args[0] === "sendHardwareOrders") {
  orderSender.sendHardwareOrder();
} else  if(args[0] === "start"){
  start();
}
