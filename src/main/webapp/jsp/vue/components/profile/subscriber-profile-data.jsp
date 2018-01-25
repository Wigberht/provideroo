<script type="text/x-template" id="subscriber-profile-data-template">
    <div>
        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_first_name
                       id="first_name"
                       type="text"
                       class="validate"
                       v-model="firstName">
                <label for="first_name">
                    {{text_first_name}}
                </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_last_name
                       id="last_name"
                       type="text"
                       class="validate"
                       v-model="lastName">
                <label for="last_name">
                    {{text_last_name}}
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_login
                       id="login"
                       type="text"
                       class="validate"
                       v-model="login">
                <label for="login">
                    {{text_login}}
                </label>
            </div>
        </div>

        <div class="row">
            <a href="" @click.prevent="updateProfile" class="btn">{{text_update}}</a>
        </div>


    </div>
</script>

<script>
    Vue.component('subscriber-profile-data', {
        props: [
            'subscriber',
            'text_login',
            'text_first_name',
            'text_last_name',
            'text_update',
            'text_update_success',
            'text_update_fail',
        ],
        template: "#subscriber-profile-data-template",
        data() {
            return {
                d_subscriber: JSON.parse(this.subscriber),
                firstName: JSON.parse(this.subscriber).firstName,
                lastName: JSON.parse(this.subscriber).lastName,
                login: JSON.parse(this.subscriber).user.login,
            }
        },
        methods: {
            updateProfile() {
                console.log("Login: " + this.login);
                axios.post("/rest/user/profile/update", {
                    'userId': this.d_subscriber.user.id,
                    'firstName': this.firstName,
                    'lastName': this.lastName,
                    'login': this.login
                }).then((response) => {
                    console.log(response);
                    Materialize.toast(this.text_update_success, 1500, "green darken-2")
                }).catch((error) => {
                    Materialize.toast(this.text_update_fail, 1500, "red darken-2");
                    console.log(error);
                })
            },
        },
        mounted: function () {
            console.log(this.d_subscriber)
        },
        computed: {}
    })
</script>
