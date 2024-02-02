import Retro from "./Retro";

//@ts-expect-error ExternalConfig comes from index.html
const debug = ExternalConfig?.debugEnabled || false;

const instance = new Retro(debug);
instance.init();