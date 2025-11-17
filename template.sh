#Creating the folder structure

mkdir -p src/main/java/com/hch/hch_backend/modules
mkdir -p src/main/java/com/hch/hch_backend/security
mkdir -p src/main/java/com/hch/hch_backend/common
mkdir -p src/main/java/com/hch/hch_backend/config

#Create sub folders for modules

mkdir -p src/main/java/com/hch/hch_backend/modules/auth
mkdir -p src/main/java/com/hch/hch_backend/modules/users
mkdir -p src/main/java/com/hch/hch_backend/modules/patients
mkdir -p src/main/java/com/hch/hch_backend/modules/doctors
mkdir -p src/main/java/com/hch/hch_backend/modules/staff
mkdir -p src/main/java/com/hch/hch_backend/modules/appointments
mkdir -p src/main/java/com/hch/hch_backend/modules/visitors
mkdir -p src/main/java/com/hch/hch_backend/modules/hospital

# Create sub folders inside each modules

for module in auth users patients doctors staff appointments visitors hospital
do
    mkdir -p src/main/java/com/hch/hch_backend/modules/$module/controller
    mkdir -p src/main/java/com/hch/hch_backend/modules/$module/service
    mkdir -p src/main/java/com/hch/hch_backend/modules/$module/entity
    mkdir -p src/main/java/com/hch/hch_backend/modules/$module/repository
    mkdir -p src/main/java/com/hch/hch_backend/modules/$module/dto
done

#Create sub folders for security

mkdir -p src/main/java/com/hch/hch_backend/security/userdetails
mkdir -p src/main/java/com/hch/hch_backend/security/jwt
mkdir -p src/main/java/com/hch/hch_backend/security/config

#Create sub folders for common

mkdir -p src/main/java/com/hch/hch_backend/common/exceptions
mkdir -p src/main/java/com/hch/hch_backend/common/utils
mkdir -p src/main/java/com/hch/hch_backend/common/constants
mkdir -p src/main/java/com/hch/hch_backend/common/base

#Creating files in config folder

touch src/main/java/com/hch/hch_backend/config/SwaggerConfig.java
touch src/main/java/com/hch/hch_backend/config/WebConfig.java