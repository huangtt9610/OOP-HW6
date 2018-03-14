import java.util.*;
/**
 * 
 * @author huangtt
 *
 */
public class NQueen {
	
	NQueen(int num){
		board=new int[num][num];
		index= new int[num];
		n=num;
	}
	
	void print() {
		numOfSolution++;
		StringBuilder s= new StringBuilder();
		//learn print StringBuilder from https://stackoverflow.com/questions/3615721/how-to-use-the-tostring-method-in-java
		for(int[] x: board) {
			for(int y: x)
				s.append(y + " ");
			
			s.append("\n");
		}
		
		System.out.println(s);
	}
	
	boolean checksafety(int[][] board, int column, int row) {
		int upperRow=row, lowerRow=row, upperColumn=column, lowerColumn=column;
		
		for(int i=column; i>=0; i--) {
			if(board[row][i]==1)
				return false;
			
			if(upperRow>=0 && upperColumn>=0) {
				if(board[upperRow--][upperColumn--]==1)
					return false;
			}
			if(lowerRow<n && lowerColumn>=0) {
				if(board[lowerRow++][lowerColumn--]==1)
					return false;
			}
		}
		return true;
	}
	
	void findNQueen() {
		int column=0,row=0, placedQ=0;
		while(index[0]<n) {	
			placedQ=0;
			for(row=index[column]; row<n; row++) {
				if(checksafety(board, column, row)) {
					placedQ++;
					index[column]=row;
					board[row][column]=1;
					break;
				}		
			}
			
			if(row==n && placedQ==0) {
				index[column]=0;
				column--;
				board[index[column]][column]=0;
				index[column]++;
		
				if(index[column]==n) {
					index[column]=0;
					column--;
					if(column<0)
						break;
					board[index[column]][column]=0;
					index[column]++;
				}
			}
			else {
				if(++column==n) {		
					print();
					board[index[--column]][column]=0;
					index[column]++;
					
				}				
			}
		
		}
		
	}
	
	int[][] board;
	int[] index;
	int n;
	int numOfSolution=0;
	
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the number of queen(s)");
		
		NQueen n=new NQueen(s.nextInt());
		n.findNQueen();
		System.out.println("Total number of solutions: "+ n.numOfSolution);
		s.close();
	
	}
}
