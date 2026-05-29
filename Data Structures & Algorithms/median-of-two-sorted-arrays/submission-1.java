class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      // base conditions
      if(nums1.length == 0) {
        return nums2.length%2==0? (double)(nums2[(nums2.length/2)-1]+nums2[nums2.length/2])/2: (double)nums2[nums2.length/2];
      }else if(nums2.length == 0) {
        return nums1.length%2==0? (double)(nums1[(nums1.length/2)-1]+nums1[nums1.length/2])/2: (double)nums1[nums1.length/2];
      }
      int mid1=-1,mid2=-2,low1=0, high1=nums1.length-1, low2=0,high2=nums2.length-1;
      int medianLen = 1+ ((nums1.length+nums2.length)/2);
      // start with smaller array
      boolean binarySearchArr1 = nums1.length<=nums2.length;
      boolean isEvenLen = (nums1.length + nums2.length)%2 == 0;
      double median=0;
      
      
      while(low1<=high1 && low2<=high2) {
        if(binarySearchArr1) {
          mid1 = (low1+high1)/2; //0(1)
          mid2 = medianLen-(mid1+1)-1;
        } else {
          mid2 = (low2+high2)/2;
          mid1 = medianLen-(mid2+1)-1;
        }
        // valid split 
        if((mid2+1==nums2.length || nums1[mid1] <= nums2[mid2+1]) && (mid1+1==nums1.length || nums2[mid2] <=nums1[mid1+1])) {
          median = computeMedianForCurrentSplit(nums1, nums2, low1, mid1, low2, mid2, isEvenLen);
          break;
        } else {
          // invalid split, shrink one of the arrays and expand the other
          if(mid2+1 < nums2.length && nums1[mid1] > nums2[mid2+1]) {
            // shrink nums1 and expand nums2
            binarySearchArr1 = true;
            if(mid1==0) {
                median = computeMedianInSingleArray(nums2, isEvenLen, medianLen);
            }
            high1 = mid1-1;
          } else if(mid1+1 < nums1.length && nums2[mid2] > nums1[mid1+1]) {
            binarySearchArr1 = false;
            if(mid2==0) {
                median = computeMedianInSingleArray(nums1, isEvenLen, medianLen);
            }
            high2=mid2-1;
            
          }
        }
      }
      return median;
    }

    private double computeMedianInSingleArray(int[] nums, boolean isEven, int medianLen) {
        if(isEven) {
            // pick last two elements
            return ((double)(nums[medianLen-1] + nums[medianLen-2])/2);
        } else {
            return (double)(nums[medianLen-1]);
        }
    }
  
  	private double computeMedianForCurrentSplit(int[] nums1, int[] nums2, int low1, int mid1, int low2, int mid2, boolean isEvenLen) {
      double median=0;
      if(isEvenLen) {
        // pick last two elements
        int firstElement = Math.max(nums1[mid1], nums2[mid2]);
        int secondElement = nums1[mid1] > nums2[mid2]? (mid1-1>=0?  Math.max(nums1[mid1-1], nums2[mid2]): nums2[mid2]): (mid2-1>=0? Math.max(nums1[mid1], nums2[mid2-1]): nums1[mid1]);
        System.out.println("First and second lemeents are "+ firstElement + " "+secondElement);
        median = ((double)(firstElement+secondElement)/2);
      } else {
        median = (double)Math.max(nums1[mid1], nums2[mid2]);
      }
      return median;
    }
}
