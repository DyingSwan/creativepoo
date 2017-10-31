import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var N = 0
var M = 0
var map = Array<Array<Int>>(0, {Array<Int>(0, {0})})
val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)

fun main(args: Array<String>){
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	var line = br.readLine().split(" ")
	N = line[0].toInt(); M = line[1].toInt()
	map = Array<Array<Int>>(N, {Array<Int>(M, {0})})
	for(i in 0 until N){
		line = br.readLine().split(" ")
		for(j in 0 until M) map[i][j] = line[j].toInt()
	}
	bw.write(buildWall(map, 0).toString())

	bw.close()
	br.close()
}

fun buildWall(map: Array<Array<Int>>, step: Int): Int {
	if(step == 3) return getSafe(map)

	var safe = -1
	for(i in 0 until N)
		for(j in 0 until M)
			if(map[i][j]==0){
				map[i][j] = 1
				safe = Math.max(buildWall(map, step+1), safe)
				map[i][j] = 0
			}
	return safe
}

fun getSafe(map: Array<Array<Int>>): Int {
	var area = 0
	var backup = copy(map)

	for(i in 0 until N)
		for(j in 0 until M)
			if(backup[i][j]==2)
				spreadVirus(backup, i, j)

	for(i in 0 until N)
		for(j in 0 until M)
			if(backup[i][j]==0) area++
	
	return area
}

fun spreadVirus(map: Array<Array<Int>>, x: Int, y: Int) {
	for(i in 0 until 4){
		var ax = x+dx[i]
		var ay = y+dy[i]
		if(isInRange(ax, ay) && map[ax][ay]==0){
			map[ax][ay] = 2
			spreadVirus(map, ax, ay)
		}
	}
}

fun isInRange(x: Int, y: Int): Boolean {
	return (0<=x&&x<N) && (0<=y&&y<M)
}

fun copy(map: Array<Array<Int>>): Array<Array<Int>> {
	var newArr = Array<Array<Int>>(N, {Array<Int>(M, {0})})
	for(i in 0 until N)
		for(j in 0 until M)
			newArr[i][j] = map[i][j]
	return newArr
}
