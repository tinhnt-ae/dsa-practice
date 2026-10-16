# Try-with-resources cold-write

Mục tiêu của bài này là tự viết lại cú pháp `try-with-resources`
và quan sát hành vi thật khi chạy test.

## Cách luyện

1. Chạy test gốc để xác nhận môi trường.
2. Mở `Exercise.java` và xóa code giữa các comment `COLD WRITE`.
3. Tự viết lại hai method mà không xem mẫu.
4. Chạy lại test, sau đó đọc output để sửa từng case fail.

Chạy từ thư mục gốc của repository:

```sh
make run-exercise EXERCISE=try-with-resources
```

Test kiểm tra resource luôn được đóng, nhiều resource được đóng theo
thứ tự ngược lại, và exception từ `close()` trở thành suppressed exception
khi phần thân `try` đã ném exception trước đó.
