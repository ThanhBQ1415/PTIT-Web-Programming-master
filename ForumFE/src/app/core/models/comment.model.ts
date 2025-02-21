import { CommentVote } from "./CommentVote.model";
import { Profile } from "./profile.model";

export interface Comment {
  id: number;
  body: string;
  postId: number;
  userId: number;
  createdAt: string;
  username: string;
  commentVotes: CommentVote[];
  upVote: boolean;
  downVote: boolean;
  cntUpVote: number;
  cntDownVote: number;
}
