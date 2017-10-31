import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Queue
import java.util.LinkedList

var N = 0
var M = 0
var V = 0
var map = Array<LinkedList<Int>>(0, {LinkedList<Int>()})
var sb = StringBuilder()
var visit = Array<Boolean>(0, {false})

fun main(args: Array<String>){
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	var line = br.readLine().split(" ")
	N = line[0].toInt(); M = line[1].toInt(); V = line[2].toInt()-1
	map = Array<LinkedList<Int>>(N, {LinkedList<Int>()})
	visit = Array<Boolean>(N, {false})
	for(i in 1..M){
		line = br.readLine().split(" ")
		var a = line[0].toInt()-1
		var b = line[1].toInt()-1
		map[a].add(b); map[b].add(a)
	}
	dfs(V)
	sb.append("\n")
	bfs()
	bw.write(sb.toString())

	bw.close()
	br.close()
}

fun dfs(cur: Int) {
	visit[cur] = true
	sb.append("${cur+1} ")

	for(i in map[cur]){
		if(!visit[i]){
			visit[i] = true
			dfs(i)
		}
	}
}

fun bfs() {
	visit = Array<Boolean>(N, {false})
	visit[V] = true
	var que = LinkedList<Int>()
	que.offer(V)

	while(!que.isEmpty()){
		var cur = que.pop()
		sb.append("${cur+1} ")
		for(i in map[cur])
			if(!visit[i]){
				que.offer(i);
				visit[i] = true
			}
	}
}
