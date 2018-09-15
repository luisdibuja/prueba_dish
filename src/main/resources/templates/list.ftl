<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Usuario </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.mensajeExitoso">{{ctrl.mensajeExitoso}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.mensajeDeError">{{ctrl.mensajeDeError}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.usuario.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nombre</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.usuario.nombre" id="uname" class="username form-control input-sm" placeholder="Ingresa tu nombre" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="edad">Edad</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.usuario.edad" id="edad" class="form-control input-sm" placeholder="Ingresa tu edad." required ng-pattern="ctrl.soloEnteros"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="salario">Salario</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.usuario.salario" id="salario" class="form-control input-sm" placeholder="Ingresa tu salario." required ng-pattern="ctrl.soloNumeros"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.usuario.id ? 'Crear' : 'Actualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpiar forma</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Usuarios </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOMBRE</th>
		                <th>EDAD</th>
		                <th>SALARIO</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.obtenerTodosLosUsuarios()">
		                <td>{{u.id}}</td>
		                <td>{{u.nombre}}</td>
		                <td>{{u.edad}}</td>
		                <td>{{u.salario}}</td>
		                <td><button type="button" ng-click="ctrl.editarUsuario(u.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.borrarUsuario(u.id)" class="btn btn-danger custom-width">Borrar</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>