export interface PostVote {
  id: number;
  updatedAt: Date;
  userId: number;
  postId: number;
  upVote: boolean;
}
