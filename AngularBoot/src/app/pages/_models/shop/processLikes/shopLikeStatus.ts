export class ShopLikeStatus {

    private status: boolean;
    private message: string;

    public getStatus(): boolean {
        return this.status;
    }

    public setStatus(status: boolean) {
        this.status = status;
    }

    public getMessage(): string {
        return this.message;
    }

    public setMessage(message: string) {
        this.message = message;
    }
}
