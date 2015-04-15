// Generated by CoffeeScript 1.9.1
(function() {
  angular.module('calendar').controller('EventController', function($scope, $modalInstance, event) {
    return new ((function() {
      function _Class() {
        $scope.event = event;
        $scope.save = this.save;
        $scope.cancel = this.cancel;
      }

      _Class.prototype.save = function() {
        return $modalInstance.close($scope.event);
      };

      _Class.prototype.cancel = function() {
        return $modalInstance.dismiss();
      };

      return _Class;

    })());
  });

}).call(this);

//# sourceMappingURL=EventController.js.map
