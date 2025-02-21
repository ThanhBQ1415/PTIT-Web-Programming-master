<h1>Báo cáo bài tập lớn lập trình web</h1>
<div>Chủ đề: Hệ thống hỏi đáp online</div>

<h2>1. Thành viên và nhiệm vụ</h2>
<ul>
  <li>Nguyễn Tiến Hải Ninh - B19DCCN458: Phân tích, thiết kế hệ thống, cơ sở dữ liệu. Lập trình backend, tạo các API. Lập trình Frontend, tạo giao diện người dùng</li>
  <li>Nguyễn Quang Thiện - B19DCCN668: Không tham gia</li>
  <li>Nguyễn Thế Vinh - B19DCCN721: Không tham gia</li>
</ul>

<h2>2. Mô tả thiết kế hệ thống</h2>
<ul>
  <li>Đăng kí tài khoản mới, đăng nhập hệ thống</li>
  <li>Hệ thống bao gồm các lớp thực thể: Câu hỏi (Post), Câu trả lời (Comment), Đánh giá câu hỏi/câu trả lời (PostVote/CommentVote), Người dùng (User), Chủ đề (Tag)</li>
  <li>Dưới đây là mô tả các bảng cơ sở dữ liệu:</li>
  
  <img src="https://user-images.githubusercontent.com/81175577/171462142-8eda2881-d755-4ad5-9a0b-9bc55996a789.jpg">
</ul>

<h2>3. Mô tả chức năng hệ thống</h2>
<ul>
  <li>Người dùng chưa đăng nhập: Xem câu hỏi/câu trả lời/các đánh giá/thông tin các người dùng</li>
  <li>Người dùng đã đăng nhập: Có các chức năng trên. Có thêm các chức năng thêm/sửa/xóa câu hỏi, câu trả lời, thông tin của bản thân. Đánh giá hữu ích hoặc không hữu ích với các câu hỏi, câu trả lời</li>
  <li>Quản trị viên: Quản lý thống kê người dùng, các chủ đề,...</li>
</ul>
<h2>4. Một số màn hình demo</h2>
<div>Đăng kí tài khoản mới</div>
<img src="https://user-images.githubusercontent.com/81175577/171471055-22bb91be-df7c-45e6-95a5-c2965182ee7c.png">
<div>Đăng nhập</div>
<img src="https://user-images.githubusercontent.com/81175577/171471229-75e86c48-a240-4fee-a1da-b665e33e51e4.png">

<div>Giao diện hiển thị các câu hỏi gồm: Tiêu đề, chủ đề, đánh giá, tác giả,...</div>
<img src="https://user-images.githubusercontent.com/81175577/171471562-ac346af0-fd1f-4840-a035-73453eb241cc.png" alt="">
<div>Tạo câu hỏi mới</div>
<img src="https://user-images.githubusercontent.com/81175577/171473665-6ea08dc9-0411-4957-82d2-35f2c179a5d9.png">
<div>Xem chi tiết câu hỏi, đăng câu trả lời, đánh giá các câu trả lời</div>
 <img src="https://user-images.githubusercontent.com/81175577/171473877-6813fbe8-073f-4d8d-8cde-7207f5c0f274.png">
 <img src="https://user-images.githubusercontent.com/81175577/171474015-56d62e16-373b-4c1a-962f-1674e32b1af6.png">
<div>Sửa thông tin</div>
<img src="https://user-images.githubusercontent.com/81175577/171474200-13adebc3-a087-4028-9f41-5456b24751b4.png">

<h2>5. Công nghệ sử dụng</h2>
<img src="https://user-images.githubusercontent.com/81175577/171474477-82f6a6bf-2313-4ad7-8f01-b56842ae6f11.png" alt="">
<ul>
  <li>Backend: Sử dụng Spring Boot 2.7, lập trình trên IntelliJ IDEA</li>
  <li>Frontend: Sử dụng Angular 12, lập trình trên Visual Studio Code</li>
  <li>DBMS: MySQL 8.0.26</li>
