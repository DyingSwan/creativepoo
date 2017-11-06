import copy
nums = []
op =[] 

def getInput():
    global nums
    global op
    n = int(input())
    nums = [int(i) for i in input().split()]
    op = [int(i) for i in input().split()]
def calc(index,op):
    tmpOp = copy.deepcopy(op)
    res = []
    t = nums[index]
    if index == 0:
        res.append(t)
        return res
    else :
        for i in range(4):
            if tmpOp[i] !=0:
                tmpOp[i]-=1
                if i == 0 :
                    for j in calc(index-1, tmpOp):
                        res.append(j+t)
                    tmpOp[i]+=1
                elif i == 1 :
                    for j in calc(index-1, tmpOp):
                        res.append(j-t)
                    tmpOp[i]+=1
                elif i == 2 :
                    for j in calc(index-1, tmpOp):
                        res.append(j*t)
                    tmpOp[i]+=1
                else :
                    for j in calc(index-1, tmpOp):
                        if j<0 :
                            res.append(-(-j//t))
                        else : res.append(j//t)
                    tmpOp[i]+=1
        return res
            

getInput() 
answer = calc(len(nums)-1,op)
print (max(answer))
print (min(answer))
