import java.util.HashSet;
import java.util.Set;
public class Player extends Mover
{
  
  char direction;//hướng di chuyển của người chơi
  char currDirection;//hướng người chơi đang di chuyển hiện tại
  char desiredDirection;//hướng người chơi muốn di chuyển

  
  int pelletsEaten;// số lượng viên gạch đã ăn

  //vị trí cuối cùng của ng chơi trên map
  int lastX;
  int lastY;
 
  //vị trí người chơi hiện tại 
  int x;
  int y;
 
  // vị trí của viên gạch mà pacman đang đứg trên 
  int pelletX;
  int pelletY;

  // di chuyển xuyên map
  boolean teleport;
  
  //  dừng lại khi gặp phải chướng ngại vật hoặc die 
  boolean stopped = false;

  //khởi tạo vị trí pacman
  public Player(int x, int y)
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
    teleport=false;
    pelletsEaten=0;
    pelletX = x/gridSize-1;
    pelletY = y/gridSize-1;
    this.lastX=x;
    this.lastY=y;
    this.x = x;
    this.y = y;
    currDirection='L';
    desiredDirection='L';
  }


  //xác định hướng di chuyển cho người chơi
  public char newDirection()
  { 
     int random;
     char backwards='U';
     int newX=x,newY=y;
     int lookX=x,lookY=y;
     Set<Character> set = new HashSet<Character>();//lưu trữ các hướng đã thử 
    switch(direction)//xác định hướng ngược lại với hướng hiện tại
    {
      case 'L':
         backwards='R';
         break;     

      case 'R':
         backwards='L';
         break;     
      case 'U':
         backwards='D';
         break;     
      case 'D':
         backwards='U';
         break;     
    }
     char newDirection = backwards;
     while (newDirection == backwards || !isValidDest(lookX,lookY))
     //Vòng lặp này sẽ tiếp tục cho đến khi tìm được một hướng mới không phải là hướng ngược lại và là một điểm đến hợp lệ.
     {
       if (set.size()==3)
       {
         newDirection=backwards;
         break;
       }
       newX=x;
       newY=y;
       lookX=x;
       lookY=y;
       random = (int)(Math.random()*4) + 1;//tạo một ramdoom từ 1 đến 4 để chọn hướng đi
       if (random == 1)
       {
         newDirection = 'L';
         newX-=increment; 
         lookX-= increment;
       }
       else if (random == 2)
       {
         newDirection = 'R';
         newX+=increment; 
         lookX+= gridSize;
       }
       else if (random == 3)
       {
         newDirection = 'U';
         newY-=increment; 
         lookY-=increment;
       }
       else if (random == 4)
       {
         newDirection = 'D';
         newY+=increment; 
         lookY+=gridSize;
       }
       if (newDirection != backwards)
       {
         set.add(new Character(newDirection));//thêm hướng mới vào set
       }
     } 
     return newDirection;
  }

  //kiểm tra xem người chơi có đang ở vị trí có thể chọn hướng hay ko
  public boolean isChoiceDest()
  {
    if (  x%gridSize==0&& y%gridSize==0 )
    {
      return true;
    }
    return false;
  }

  // test di chuyển trc
  public void demoMove()
  {
    //lưu vị trí hiện tại
    lastX=x;
    lastY=y;
    //kiểm tra xe người chơi có thể chuyển hướng hay ko
    if (isChoiceDest())
    {
      direction = newDirection();
    }
    switch(direction)
    {
      case 'L':
         if ( isValidDest(x-increment,y))
         {
           x -= increment;
         }
         else if (y == 9*gridSize && x < 2 * gridSize)
         {
           x = max - gridSize*1;
           teleport = true; 
         }
         break;     
      case 'R':
         if ( isValidDest(x+gridSize,y))
         {
           x+= increment;
         }
         else if (y == 9*gridSize && x > max - gridSize*2)
         {
           x = 1*gridSize;
           teleport=true;
         }
         break;     
      case 'U':
         if ( isValidDest(x,y-increment))
           y-= increment;
         break;     
      case 'D':
         if ( isValidDest(x,y+gridSize))
           y+= increment;
         break;     
    }
    currDirection = direction;//cập nhật hướng di chuyển hiện tại
    frameCount ++; //tăng số frame để kiểm soát animation
  }

  //di chuyển pacman
  public void move()
  {
    int gridSize=20;
    lastX=x;
    lastY=y;
     
    //thay đổi hướng di chuyển
    if (x %20==0 && y%20==0 ||
       (desiredDirection=='L' && currDirection=='R')  ||
       (desiredDirection=='R' && currDirection=='L')  ||
       (desiredDirection=='U' && currDirection=='D')  ||
       (desiredDirection=='D' && currDirection=='U')
       )
    {
      switch(desiredDirection)
      {
        case 'L':
           if ( isValidDest(x-increment,y))
             x -= increment;
           break;     
        case 'R':
           if ( isValidDest(x+gridSize,y))
             x+= increment;
           break;     
        case 'U':
           if ( isValidDest(x,y-increment))
             y-= increment;
           break;     
        case 'D':
           if ( isValidDest(x,y+gridSize))
             y+= increment;
           break;     
      }
    }
    // nếu ko đổi hướng thì sẽ vẫn di chuyển theo hướng hiện tại
    else
    if (lastX==x && lastY==y)
    {
      switch(currDirection)
      {
        case 'L':
           if ( isValidDest(x-increment,y))
             x -= increment;
           else if (y == 9*gridSize && x < 2 * gridSize)
           {
             x = max - gridSize*1;
             teleport = true; 
           }
           break;     
        case 'R':
           if ( isValidDest(x+gridSize,y))
             x+= increment;
           else if (y == 9*gridSize && x > max - gridSize*2)
           {
             x = 1*gridSize;
             teleport=true;
           }
           break;     
        case 'U':
           if ( isValidDest(x,y-increment))
             y-= increment;
           break;     
        case 'D':
           if ( isValidDest(x,y+gridSize))
             y+= increment;
           break;     
      }
    }

    //nếu đổi hướng thì currDirection sẽ dc cập nhật
    else
    {
      currDirection=desiredDirection;
    }
   
    //nếu ko di chuyển thì stopped=true 
    if (lastX == x && lastY==y)
      stopped=true;
  
    //nếu di chuyển stopped=false và framecount tăng lên
    else
    {
      stopped=false;
      frameCount ++;
    }
  }

  //cập nhật vị trí viên gạch mà pacman đang cố ăn
  public void updatePellet()
  {
    if (x%gridSize ==0 && y%gridSize == 0)
    {
    pelletX = x/gridSize-1;
    pelletY = y/gridSize-1;
    }
  } 
}
