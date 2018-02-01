import {RemoveLikeStatus} from './removeLikeStatus';

export class RemoveLikeResponse {

    private userId: string;
    private shopId: string;
    private likeStatus: RemoveLikeStatus;

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

    public getLikeStatus(): RemoveLikeStatus {
        return this.likeStatus;
    }

    public setLikeStatus(likeStatus: RemoveLikeStatus) {
        this.likeStatus = likeStatus;
    }
}
