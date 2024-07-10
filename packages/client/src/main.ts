import Client from "./Client.ts";

//@ts-expect-error ExternalConfig comes from index.html
const debug = ExternalConfig?.debugEnabled || false;
const instance = new Client(debug);
instance.init();
