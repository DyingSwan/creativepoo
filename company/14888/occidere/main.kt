import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var N = 0
var A = Array<Int>(0, {0})
var ops = Array<Int>(0, {0})
var min = 0x7fffffff
var max = -min

fun main(args: Array<String>){
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	N = br.readLine().toInt()
	A = Array<Int>(N, {0})
	ops = Array<Int>(N-1, {0})
	var line = br.readLine().split(" ")
	for(i in 0 until N)
		A[i] = line[i].toInt()
	line = br.readLine().split(" ")
	var k = 0; var l = 0
	for(i in 0..3){
		l = line[i].toInt()
		for(j in 0 until l) ops[k++] = i
	}
	dfs("")
	bw.write("%d\n%d".format(max, min))

	bw.close()
	br.close()
}

fun dfs(op: String) {
	if(op.length == N-1){
		calc(op)
		return
	}
	var k = 0
	for(i in 0 until N-1){
		if(ops[i] == -1) continue
		k = ops[i]; ops[i] = -1
		if(k==0) dfs(op+'+')
		else if(k==1) dfs(op+'-')
		else if(k==2) dfs(op+'*')
		else dfs(op+'/')
		ops[i] = k;
	}
}

fun calc(op: String){
	var ch = '0'
	var a = A[0]
	var b = 0
	for(i in 0 until N-1){
		ch = op[i]; b = A[i+1]
		if(ch=='+') a+=b
		else if(ch=='-') a-=b
		else if(ch=='*') a*=b
		else a/=b
	}
	max = Math.max(max, a);
	min = Math.min(min, a);
}