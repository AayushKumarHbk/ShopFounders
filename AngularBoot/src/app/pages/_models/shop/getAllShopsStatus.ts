export class GetAllShopsStatus {

    private status: boolean;
    private message: string;

    public setStatus(status: boolean) {
        this.status = status;
    }

    public getStatus(): boolean {
        return this.status;
    }

    public getMessage() {
        return this.message;
    }

    public setMessage(message: string) {
        this.message = message;
    }
}
