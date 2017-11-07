import java.util.*;
import java.io.*;

public class Main{
	static int[][] map;
	static int x;
	static int y;
	static int check = 0;
	static int max = 987654321;
	static int min = -max;
	static int room = 0;
	//움직이는 방향 상 -> 좌 -> 하 -> 우 반시계 방향.
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void main(String[] args) throws Exception{
		String[] s = in.readLine().split(" ");

		y = Integer.parseInt(s[0]);
		x = Integer.parseInt(s[1]);

		map = new int[x][y];
	
		for(int i = 0; i < y; i++){
			s = in.readLine().split(" ");
			for(int j = 0; j < x; j++){
				map[j][i] = Integer.parseInt(s[j]);
			}
		}
		pillar(0,0,map);

		System.out.println(room);
		
	}//main 함수 끝.

	//기둥 함수
	public static void pillar(int x, int y,int[][] k){
		int[][] tmp = new int[x][y];
		//임시 배열 만들기
		for(int i = 0; i < y; i++)
			for(int j = 0; j < x; j++)
				tmp[j][i] = k[j][i];

		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				if(check == 3){//기둥이 3개가 세워졌을 경우
					i -= 1;//4번째 기둥을 박는 순서이므로 1을 줄여주어야 함.
					j -= 1;
					check = 0;//기둥 갯수 초기화
					virus(tmp,0,0);//바이러스 살포
				}
				else{
					if(tmp[j][i] == 1 || tmp[j][i] == 2 ||tmp[j][i] == -1)//이미 기둥이 있거나 바이러스가 있는 위치일 경우 패스
						continue;
					else{
					tmp[j][i] = -1;//현재 위치에 기둥.
					check += 1;//기둥 갯수 추가
					System.out.println("SDSADA");
					pillar(j,i,tmp);//다음 기둥 설치
					}
				}
					
			}
		}
	}//기둥 함수 끝

	//바이러스 함수 시작
	public static void virus(int[][] k, int sx, int sy){
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; x++){
				if(k[j][i] == 1 || k[j][i] == -1)//기둥이 있을 경우 패스
					continue;
				else if(k[j][i] == 2){
					for(int n = 0; n < 4; n++){
						int nx = j + dx[n];
						int ny = i + dy[n];
						if(k[nx][ny] == 0){//현재 자리 기준 상하좌우 확인해서 전파 가능할 경우
							k[nx][ny] = 2;//전파
							virus(k,nx,ny);//새로 전파된 자리 기준으로 다시 전파.
							}
						}
					}
				}
			for(int a = 0; a < y; a++){
				for(int b = 0; b < x; b++){
					System.out.print(k[b][a]);
				}
				System.out.println();

			}
			}


			//바이러스 전파 완료 이후 안전 장소 갯수 검사.
		//	for(int a = 0; a < y; a++)
		//		for(int b = 0; b < x; b++){
		//			if(k[b][a] == 0)
		//				room += 1;
		//		}
		//	max = Math.max(room,max);
		//	min = Math.min(room,min);

		}//바이러스 함수 끝	

}//Main.java의 끝
