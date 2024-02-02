export default interface IncomingMessage {
    parse(data: unknown): void;
}