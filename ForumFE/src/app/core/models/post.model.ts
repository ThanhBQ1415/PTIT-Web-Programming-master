import { PostVote } from "./PostVote.model";

export interface Post {
  id: number;
  title: string;
  body: string;
  createdAt: Date;
  updateAt: string;
  closedAt: string;
  userId: number;
  tags: [];
  postVotes: PostVote[];
  open: boolean;
  username: string;
  upVote: boolean;
  downVote: boolean;
}
