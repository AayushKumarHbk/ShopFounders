import {ShopLikeStatus} from './shopLikeStatus';

export class ShopLikeResponse {

    private userId: string;
    private shopId: string;
    private likeType: number;
    private likeStatus: ShopLikeStatus;

    public getUserId(): String {
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

    public getLikeType(): number {
        return this.likeType;
    }

    public setLikeType(likeType: number) {
        this.likeType = likeType;
    }

    public getLikeStatus(): ShopLikeStatus {
        return this.likeStatus;
    }

    public setLikeStatus(likeStatus: ShopLikeStatus) {
        this.likeStatus = likeStatus;
    }
}
