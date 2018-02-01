export class RemoveLikeRequest {

    private userId: string;
    private shopId: string;

    constructor(userId: string, shopId: string) {
        this.userId = userId;
        this.shopId = shopId;
    }

    public getUserId(): string {
        return this.userId;
    }

    public setUserId(userId: string) {
        this.userId = userId;
    }

    public getShopId(): string {
        return this.shopId;
    }

    public setShopId(shopId: string) {
        this.shopId = shopId;
    }
}
