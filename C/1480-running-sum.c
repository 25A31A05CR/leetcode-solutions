int* runningSum(int* nums, int numsSize, int* returnSize) {
    
      * returnSize=numsSize;
    int *ans = malloc(numsSize * sizeof(int));
  for(int i=0;i<numsSize;i++){
    int k=0;
    for(int j=0;j<=i;j++){
        k+=nums[j];
  }
    ans[i]=k;
  }
  return ans;
