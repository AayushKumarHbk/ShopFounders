export class LoginStatus {
    private status: boolean;

    public setStatus(status: boolean) {
        this.status = status;
    }

    public getStatus(): boolean {
        return this.status;
    }
}
