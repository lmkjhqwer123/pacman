

/* phương thúc di chuyển cho ghost và player*/
public class Mover{
  /* framecount dùng để đếm khung hình động*/
  int frameCount=0;

  /* bản đồ trò chơi */
  boolean[][] state;

  /* gridSize là kích thước trò chơi 20*20
     max là chiều cao và rộng của trò chơi
     increment là tốc độ di chuyển của ghost và player
     1 increment được thực hiện mỗi lần move() dc gọi đến */
  int gridSize;
  int max;
  int increment;

  /* cấu trúc cách di chuyển */
    public Mover()
    {
        gridSize=20;
        increment = 4;
        max = 400;
        state = new boolean[20][20];
        for(int i =0;i<20;i++)
        {
        for(int j=0;j<20;j++)
        {
            state[i][j] = false;
        }
        }
    }

  /* cập nhật thông tin bản đồ */
  public void updateState(boolean[][] state)
  {
    for(int i =0;i<20;i++)
    {
      for(int j=0;j<20;j++)
      {
        this.state[i][j] = state[i][j];
      }
    }
  }

  /*xác định xem điểm đến kế tiếp có hợp lệ hay ko.*/
  public boolean isValidDest(int x, int y)
  {
    /*kiểm tra xem x và y có nằm trong giới hạn bản đồ hay ko và liệu điểm đấy có hợp lê ko */
    if ((((x)%20==0) || ((y)%20)==0) && 20<=x && x<400 && 20<= y && y<400 && state[x/20-1][y/20-1] )
    {
      return true;
    }
    return false;
  } 
}