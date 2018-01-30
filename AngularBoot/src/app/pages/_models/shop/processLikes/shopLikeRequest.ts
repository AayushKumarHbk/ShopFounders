export class ShopLikeRequest {

    private userId: string;
    private shopId: string;
    private likeType: number;

    constructor(userId: string, shopId: string, likeType: number) {
        this.userId = userId;
        this.shopId = shopId;
        this.likeType = likeType;
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

    public getLikeType() {
        return this.likeType;
    }

    public setLikeType(likeType: number) {
        this.likeType = likeType;
    }
}
