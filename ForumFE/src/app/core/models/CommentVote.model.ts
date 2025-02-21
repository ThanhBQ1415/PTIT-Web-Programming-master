export interface CommentVote {
  id: number;
  updatedAt: Date;
  userId: number;
  commentId: number;
  upVote: boolean;
}
